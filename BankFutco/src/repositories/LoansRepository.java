package repositories;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import model.Loans;

public class LoansRepository {
    private final List<Loans> storage = new ArrayList<>();

    public LoansRepository() {
        initData();
    }

    private void initData() {
        storage.add(new Loans(LocalDate.now().minusMonths(6), "Personal", new BigDecimal("10000.00"), new BigDecimal("4000.00"), new BigDecimal("6000.00")));
        storage.add(new Loans(LocalDate.now().minusMonths(3), "Vehicle", new BigDecimal("20000.00"), new BigDecimal("10000.00"), new BigDecimal("10000.00")));
    }

    public Loans save(Loans loan) {
        if (loan == null || loan.getDate() == null) {
            throw new IllegalArgumentException("Loan o fecha no puede ser null");
        }
        String id = loan.getDate().toString();
        storage.removeIf(l -> l.getDate().toString().equals(id));
        storage.add(loan);
        return loan;
    }

    public Optional<Loans> findById(String id) {
        if (id == null) return Optional.empty();
        return storage.stream().filter(l -> l.getDate().toString().equals(id)).findFirst();
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage);
    }

    public boolean deleteById(String id) {
        return findById(id).map(storage::remove).orElse(false);
    }

    public boolean existsById(String id) {
        return storage.stream().anyMatch(l -> id != null && l.getDate().toString().equals(id));
    }
}
