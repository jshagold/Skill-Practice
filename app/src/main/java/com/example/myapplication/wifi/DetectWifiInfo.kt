package com.example.myapplication.wifi

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.SupplicantState
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun DetectWifiInfo() {
    val context = LocalContext.current

    val wifiInfo: MutableState<String?> = remember {
        mutableStateOf("")
    }
    val dnsString: MutableState<String?> = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDDDDDD))
    ) {
        Text(
            text = "Current Connected Wifi is \"${wifiInfo.value}\""
        )
        Text(text = "DNS State: ${dnsString.value}")
        
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                wifiInfo.value = currentConnectedWifiName(context)
                dnsString.value = getPrivateDNSStatus(context)
            }
        }) {
            Text(text = "Detect Wifi Info")
        }
    }
}

suspend fun currentConnectedWifiName(context: Context) : String? {
    // 위치 권한 확인

    if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED &&
        context.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) !== PackageManager.PERMISSION_GRANTED
    ) {
        return "위치 권한 없음"
    }

    // 위치 서비스 활성화 여부 확인
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        return "위치 서비스 비활성화됨"
    }


    val wifiManager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // todo NEARBY_WIFI_DEVICES 런타임 권한을 받아야함
        var wifiInfo: WifiInfo? = null

        val request = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
        val networkCallback = object : ConnectivityManager.NetworkCallback(FLAG_INCLUDE_LOCATION_INFO) {

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)

                wifiInfo = networkCapabilities.transportInfo as WifiInfo?
                Log.e("TAG", "onCapabilitiesChanged: ssid=${wifiInfo?.ssid}", )
            }
        }


        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.requestNetwork(request, networkCallback)
        while (wifiInfo == null) {
            delay(500)
        }
        connectivityManager.registerNetworkCallback(request, networkCallback)

        return wifiInfo?.ssid

    } else {
        val wifiInfo = wifiManager.connectionInfo
        if(wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            return wifiInfo.ssid
        }
        return wifiInfo.ssid
    }
}


fun getPrivateDNSStatus(context: Context): String {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val activeNetwork = connectivityManager.activeNetwork
        if (activeNetwork != null) {
            val linkProperties = connectivityManager.getLinkProperties(activeNetwork)
            if (linkProperties != null) {
                // DNS 서버 확인
                val dnsServers = linkProperties.dnsServers
                return if (dnsServers.isNotEmpty()) {
                    val dnsInfo = StringBuilder("DNS Servers:\n")
                    for (dns in dnsServers) {
                        dnsInfo.append(dns.hostAddress).append("\n")
                    }
                    dnsInfo.toString()
                } else {
                    "DNS 서버 정보를 가져올 수 없습니다."
                }
            }
        }
    }
    return "네트워크 연결이 활성화되지 않았습니다."
}