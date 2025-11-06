package repositories;

import java.util.*;
import java.math.BigDecimal;
import model.Cards;

public class CardsRepository {
    private final List<Cards> storage = new ArrayList<>();

    public CardsRepository() {
        initData();
    }

    private void initData() {
        storage.add(new Cards("CARD001", "Credit", new BigDecimal("3000.00"), new BigDecimal("500.00"), new BigDecimal("2500.00")));
        storage.add(new Cards("CARD002", "Debit", new BigDecimal("1000.00"), new BigDecimal("200.00"), new BigDecimal("800.00")));
    }

    public Cards save(Cards card) {
        if (card == null || card.getCardNumber() == null) {
            throw new IllegalArgumentException("Card o cardNumber no puede ser null");
        }
        storage.removeIf(c -> c.getCardNumber().equals(card.getCardNumber()));
        storage.add(card);
        return card;
    }

    public Optional<Cards> findById(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        return storage.stream().filter(c -> c.getCardNumber().equals(cardNumber)).findFirst();
    }

    public List<Cards> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String cardNumber) {
        return findById(cardNumber).map(storage::remove).orElse(false);
    }

    public boolean existsById(String cardNumber) {
        return storage.stream().anyMatch(c -> cardNumber != null && c.getCardNumber().equals(cardNumber));
    }
}
