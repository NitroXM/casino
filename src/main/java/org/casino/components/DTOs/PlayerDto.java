package org.casino.components.DTOs;

import org.casino.gameplay.Card;

import java.util.List;

public record PlayerDto(String givenName, int seatNumber, int bet, List<Card> cards) {}

