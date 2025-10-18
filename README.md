# Scopa — Custom Networked Java Card Game

**Languages:** Java  
**Topics:** Objects & Classes, Custom Data Structures, Networking, Threads, GUI, Sound, Animation  
**Project type:** 2-player networked card game 

---

## Project summary
This project is a two-player networked Java card game inspired by the traditional Italian game **Scopa** — redesigned and simplified with unique twists. Players connect over a network, exchange moves in real time, and compete to capture the most valuable cards over three rounds. All data structures (HashSet, DLList, etc.) are implemented **from scratch** — no Java collection imports are used.

---

## Features
- **Custom data structures:**  
  - `MyHashSet` — tracks deck, player hands, and table cards  
  - `DLList` — used for animated cards on the start screen  
- **Networking:**  
  - Server displays IP address and connected clients graphically (Note: You must add your IP address on line 1198 of the ClientScreen      class to properly connect a client to the server) 
  - Two clients connect and play interactively  
- **Gameplay mechanics:**  
  - Each player is dealt 5 cards  
  - 4 cards are placed face-up on the table  
  - Players can capture a single card of the same value
  - Players can **trade cards** by mutual agreement   
- **Scoring:**  
  - Tracks player points and number of rounds played  
  - After 3 rounds, the player with the most high-value captures wins  
  - Displays winner screen with sound  
- **Graphics and UI:**  
  - Start screen with animated cards (tracked using DLList)  
  - Table background and card sprites drawn with Java components  
  - Restart option to replay  
- **Sound:**  
  - Background music throughout gameplay

---

## How to run
1. **Compile:**

   javac *.java
   
   java Server


   In another terminal (command prompt)
   
   java Client


   In another terminal (command prompt)
   
   java Client
