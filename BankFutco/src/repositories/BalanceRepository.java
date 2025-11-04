package repositories;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import model.Balance;

public class BalanceRepository {
    private final List<Balance> storage = new ArrayList<>();

    public BalanceRepository() {
        initData();
    }

    private void initData() {
        // Datos iniciales de ejemplo
        storage.add(new Balance(LocalDate.now().minusDays(3), "Depósito inicial", new BigDecimal("500.00"), BigDecimal.ZERO, new BigDecimal("500.00")));
        storage.add(new Balance(LocalDate.now().minusDays(2), "Retiro", BigDecimal.ZERO, new BigDecimal("100.00"), new BigDecimal("400.00")));
        storage.add(new Balance(LocalDate.now().minusDays(1), "Depósito", new BigDecimal("200.00"), BigDecimal.ZERO, new BigDecimal("600.00")));
    }

    public Balance save(Balance balance) {
        if (balance == null || balance.getDate() == null) {
            throw new IllegalArgumentException("Balance o fecha no puede ser null");
        }
        String id = balance.getDate().toString();
        storage.removeIf(b -> b.getDate().toString().equals(id));
        storage.add(balance);
        return balance;
    }

    public Optional<Balance> findById(String id) {
        if (id == null) return Optional.empty();
        return storage.stream().filter(b -> b.getDate().toString().equals(id)).findFirst();
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String id) {
        return findById(id).map(storage::remove).orElse(false);
    }

    public boolean existsById(String id) {
        return storage.stream().anyMatch(b -> id != null && b.getDate().toString().equals(id));
    }
}
