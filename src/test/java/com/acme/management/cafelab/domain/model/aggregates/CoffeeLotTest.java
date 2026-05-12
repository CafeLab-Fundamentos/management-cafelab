package com.acme.management.cafelab.domain.model.aggregates;

import com.upc.pe.managementcafelab.coffee.domain.exceptions.InvalidNewStatusException;
import com.upc.pe.managementcafelab.coffee.domain.exceptions.LotInvariantException;
import com.upc.pe.managementcafelab.coffee.domain.model.aggregates.CoffeeLot;
import com.upc.pe.managementcafelab.coffee.domain.model.commands.CreateCoffeeLotCommand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeLotTest {

    private CoffeeLot createDisponibleLot(double weight) {

        CreateCoffeeLotCommand command =
                new CreateCoffeeLotCommand(
                        1L,
                        1L,
                        "Lote Premium",
                        "Arábica",
                        "Cusco",
                        "Disponible",
                        1500.0,
                        "Lavado",
                        weight,
                        List.of()
                );

        return new CoffeeLot(command);
    }

    private CoffeeLot createEnCuarentenaLot(double weight) {

        CreateCoffeeLotCommand command =
                new CreateCoffeeLotCommand(
                        1L,
                        1L,
                        "Lote Premium",
                        "Arábica",
                        "Cusco",
                        "En cuarentena",
                        1500.0,
                        "Lavado",
                        weight,
                        List.of()
                );

        return new CoffeeLot(command);
    }

    @Test
    void createCoffeeLotSuccessfullyTest() {

        var lot = createDisponibleLot(20.0);

        assertEquals("Lote Premium", lot.getLotName());
        assertEquals(20.0, lot.getRemainingWeight());
        assertEquals("Disponible", lot.getStatus().value());
    }

    @Test
    void advanceStatusSuccessfullyTest() {

        var lot = createEnCuarentenaLot(20.0);

        lot.advanceStatus("Disponible");

        assertEquals("Disponible", lot.getStatus().value());
    }

    @Test
    void advanceStatusShouldThrowExceptionWhenRemainingWeightIsZeroTest() {

        var lot = createEnCuarentenaLot(20.0);

        lot.setRemainingWeight(0.0);

        assertThrows(InvalidNewStatusException.class,
                () -> lot.advanceStatus("Disponible"));
    }

    @Test
    void consumeStockShouldReduceRemainingWeightTest() {

        var lot = createDisponibleLot(20.0);

        lot.consumeStock(5.0);

        assertEquals(15.0, lot.getRemainingWeight());
    }

    @Test
    void consumeStockShouldSetStatusAgotadoWhenRemainingWeightIsZeroTest() {

        var lot = createDisponibleLot(20.0);

        lot.consumeStock(20.0);

        assertEquals("Agotado", lot.getStatus().value());
    }

    @Test
    void consumeStockShouldThrowExceptionWhenStatusIsNotDisponibleTest() {

        var lot = createEnCuarentenaLot(20.0);

        assertThrows(LotInvariantException.class,
                () -> lot.consumeStock(5.0));
    }
}