package com.tellingus.tellingme.data.network.ssl

import android.content.Context
import com.tellingus.tellingme.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

@Singleton
class SelfSigningHelper @Inject constructor(
    @ApplicationContext context: Context
) {
    lateinit var tmf: TrustManagerFactory
    lateinit var sslContext: SSLContext

    init {
        val cf: CertificateFactory
        val ca: Certificate

        val caInput: InputStream

        try {
            cf = CertificateFactory.getInstance("X.509")

            caInput = context.resources.openRawResource(R.raw.tellingme_store)

            ca = cf.generateCertificate(caInput)
            println("ca = ${(ca as X509Certificate).subjectDN}")

            // Create a KeyStore containing our trusted CAs
            val keyStoreType = KeyStore.getDefaultType()
            val keyStore = KeyStore.getInstance(keyStoreType)
            with(keyStore) {
                load(null, null)
                keyStore.setCertificateEntry("ca", ca)
            }

            // Create a TrustManager that trusts the CAs in our KeyStore
            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
            tmf.init(keyStore)

            // Create an SSLContext that uses our TrustManager
            sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, java.security.SecureRandom())

            caInput.close()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}