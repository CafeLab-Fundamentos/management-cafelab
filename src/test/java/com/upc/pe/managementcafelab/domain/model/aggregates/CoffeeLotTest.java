package com.upc.pe.managementcafelab.domain.model.aggregates;

import com.upc.pe.managementcafelab.coffee.domain.exceptions.InvalidNewStatusException;
import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeLotTest {

    private CoffeeLot createGreenLot(double weight) {

        CreateCoffeeLotCommand command =
                new CreateCoffeeLotCommand(
                        1L,
                        1L,
                        "Lote Premium",
                        "Arábica",
                        "Cusco",
                        "green",
                        1500.0,
                        "Lavado",
                        weight,
                        List.of()
                );

        return new CoffeeLot(command);
    }

    private CoffeeLot createRoastedLot(double weight) {

        CreateCoffeeLotCommand command =
                new CreateCoffeeLotCommand(
                        1L,
                        1L,
                        "Lote Premium",
                        "Arábica",
                        "Cusco",
                        "roasted",
                        1500.0,
                        "Lavado",
                        weight,
                        List.of()
                );

        return new CoffeeLot(command);
    }

    @Test
    void createCoffeeLotSuccessfullyTest() {

        var lot = createGreenLot(20.0);

        assertEquals("Lote Premium", lot.getLotName());
        assertEquals(20.0, lot.getRemainingWeight());
        assertEquals("green", lot.getStatus().value());
    }

    @Test
    void advanceStatusSuccessfullyTest() {

        var lot = createRoastedLot(20.0);

        lot.advanceStatus("green");

        assertEquals("green", lot.getStatus().value());
    }

    @Test
    void advanceStatusShouldThrowExceptionWhenRemainingWeightIsZeroTest() {

        var lot = createRoastedLot(20.0);

        lot.setRemainingWeight(0.0);

        assertThrows(InvalidNewStatusException.class,
                () -> lot.advanceStatus("green"));
    }

    @Test
    void consumeStockShouldReduceRemainingWeightTest() {

        var lot = createGreenLot(20.0);

        lot.consumeStock(5.0);

        assertEquals(15.0, lot.getRemainingWeight());
    }

    @Test
    void consumeStockShouldKeepStatusWhenRemainingWeightIsZeroTest() {

        var lot = createGreenLot(20.0);

        lot.consumeStock(20.0);

        assertEquals("green", lot.getStatus().value());
    }

    @Test
    void consumeStockShouldAllowGreenStatusTest() {

        var lot = createGreenLot(20.0);
        lot.setRemainingWeight(10.0);

        assertDoesNotThrow(() -> lot.consumeStock(5.0));
        assertEquals("green", lot.getStatus().value());
    }
}
