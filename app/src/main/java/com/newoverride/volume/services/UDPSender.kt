package com.newoverride.volume.services

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object UDPSender {
    private const val SERVERIP = "167.71.19.207"
    private const val SERVERPORT = 3000
    fun sendCommand(command: String) {
        runBlocking {
            withContext(Dispatchers.IO) {
                val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).udp()
                    .connect(InetSocketAddress(SERVERIP, SERVERPORT))
                val byteArray = command.toByteArray()

                // Converte ByteArray em ByteReadPacket
                val packet = buildPacket { writeFully(byteArray) }

                socket.send(Datagram(packet, InetSocketAddress(SERVERIP, SERVERPORT)))
                socket.close()
            }
        }
    }
}