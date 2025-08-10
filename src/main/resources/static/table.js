let playerHand = 0;
let dealerHand = 0;
let dealerCardsEl = document.getElementById("dealerCards-el");
let playerCardsEl = document.getElementById("playerCards-el");

const playBtn = document.getElementById("play-btn");
const betEl = document.getElementById("bet-el");
const hitBtn = document.getElementById("hit-btn");
const standBtn = document.getElementById("stand-btn");
const doubleDownBtn = document.getElementById("doubleDown-btn");

function currentState() {
    //fetch("http://localhost:8080/api/game-state").then(response => response.json()).then(data => {})
    //or maybe use websocket
}

function startGame() {
    //Make a game instance

}

function doubleDown() {
    console.log("Hi");
}

playBtn.addEventListener("click", startGame);
//hitBtn.addEventListener("click", hit);
//standBtn.addEventListener("click", stand);
doubleDownBtn.addEventListener("click", doubleDown);
addEventListener("load", currentState);
//window.addEventListener("load", currentState);