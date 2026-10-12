class SlidingWindow {
int windowSize;
int totalPackets;
int currentPacket;

SlidingWindow(int windowSize, int totalPackets) {

this.windowSize = windowSize;
this.totalPackets = totalPackets;
this.currentPacket = 0; // Start from the first packet
}

// Method to send packets
public void sendPackets() {
while (currentPacket < totalPackets) {
// Send packets within the window limit
for (int i = 0; i < windowSize && currentPacket < totalPackets; i++) {
System.out.println("Sending packet " + currentPacket);
currentPacket++;
}
// Simulate receiving an acknowledgment
receiveAck();
}
}

// Method to simulate receiving acknowledgment for the window
private void receiveAck() {
System.out.println("Acknowledgment received for window up to packet " +
(currentPacket - 1));

}
}

public class SW {
public static void main(String[] args) {
int windowSize = 4; // Size of the sliding window
int totalPackets = 10; // Total number of packets to send

SlidingWindow slidingWindow = new SlidingWindow(windowSize,

totalPackets);
slidingWindow.sendPackets(); // Start the packet transmission
}
}

GoBackNSlidingWindow -EXTRA CODE WITH FAILURE-
import java.util.LinkedList;

import java.util.Queue; int windowSize = 4; // Size of the sliding window

int totalPackets = 10; // Total number of packets to send

class Packet {
int seqNum;

Packet(int seqNum) {
this.seqNum = seqNum;
}

@Override
public String toString() {
return "Packet " + seqNum;
}
}

class Sender {
private int windowSize;
private int base; // First unacknowledged packet
private int nextSeqNum; // Next packet to send
private Queue<Packet> packets;

Sender(int windowSize, int totalPackets) {
this.windowSize = windowSize;
this.base = 0;
this.nextSeqNum = 0;

this.packets = new LinkedList<>();

// Create all packets
for (int i = 0; i < totalPackets; i++) {
packets.add(new Packet(i));
}
}

// Method to simulate sending packets
public void sendPackets() {
while (base < packets.size()) {
// Send packets within the window

while (nextSeqNum < base + windowSize && nextSeqNum <

packets.size()) {
Packet packet = ((LinkedList<Packet>) packets).get(nextSeqNum);
System.out.println("Sender: Sending " + packet);
nextSeqNum++;
}

// Simulate receiving an acknowledgment
boolean ackReceived = simulateAck();

if (ackReceived) {

System.out.println("Sender: Acknowledgment received for packet " +

base);
base++;
} else {
// Go-Back-N: Resend all packets starting from `base`
System.out.println("Sender: Timeout! Resending from packet " + base);
nextSeqNum = base;
}
}
}

// Simulate acknowledgment with a 90% success rate
private boolean simulateAck() {
return Math.random() > 0.1; // 90% chance of success
}
}

public class GoBackNSlidingWindow {
public static void main(String[] args) {
int windowSize = 4; // Sliding window size
int totalPackets = 10; // Total number of packets to send

Sender sender = new Sender(windowSize, totalPackets);
sender.sendPackets();
}
}