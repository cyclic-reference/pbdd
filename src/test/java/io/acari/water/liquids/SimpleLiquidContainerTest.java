package io.acari.water.liquids;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class SimpleLiquidContainerTest {


    @Test
    public void constructorShouldThrowIllegalArgumentExceptionWhenGivenNegativeValue() {
        try {
            new SimpleLiquidContainer(-1);
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void fetchTotalCapacityShouldReturnValueConstructedWith() {
        long input = 500L;
        long expectedResult = 500L;
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        long result = testSubject.fetchTotalCapacity();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void storeLiquidShouldStoreReturnMaxCapacityWhenGivenValueEqualToTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        Water liquid = new Water(input);
        Liquid result = testSubject.storeLiquid(liquid);
        assertThat(result).isEqualTo(expectedResult);
        assertThat(liquid.getAmount()).isEqualTo(0L);
    }

    @Test
    public void storeLiquidShouldStoreReturnMaxCapacityWhenGivenValueGreaterThanTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        Water liquid = new Water(input + 1L);
        Liquid result = testSubject.storeLiquid(liquid);
        assertThat(result).isEqualTo(expectedResult);
        assertThat(liquid.getAmount()).isEqualTo(1L);
    }

    @Test
    public void storeLiquidShouldStoreReturnDesiredAmountWhenGivenValueLessThanTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(499L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        Water liquid = new Water(input - 1L);
        Liquid result = testSubject.storeLiquid(liquid);
        assertThat(result).isEqualTo(expectedResult);
        assertThat(liquid.getAmount()).isEqualTo(0L);
    }

    @Test
    public void storeLiquidShouldStoreReturnMaxCapacityWhenGivenValueOneLessThanTotalCapacityTwice() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        Liquid testInput = new Water(input - 1L);
        testSubject.storeLiquid(testInput);
        assertThat(testInput.getAmount()).isEqualTo(0L);
        Liquid liquid = new Liquid(input);
        Liquid result = testSubject.storeLiquid(liquid);
        assertThat(result).isEqualTo(expectedResult);
        assertThat(liquid.getAmount()).isEqualTo(input - 1L);
    }

    @Test
    public void fetchCurrentVolumeReturnMaxCapacityWhenGivenValueEqualToTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        testSubject.storeLiquid(new Water(input));
        Optional<Liquid> result = testSubject.fetchCurrentVolume();
        assertTrue(result
                .map(expectedResult::equals)
                .orElse(false));
    }

    @Test
    public void fetchCurrentVolumeReturnMaxCapacityWhenGivenValueGreaterThanTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        testSubject.storeLiquid(new Water(input + 1));
        Optional<Liquid> result = testSubject.fetchCurrentVolume();
        assertTrue(result
                .map(expectedResult::equals)
                .orElse(false));
    }

    @Test
    public void fetchCurrentVolumeReturnDesiredAmountWhenGivenValueLessThanTotalCapacity() {
        long input = 500L;
        Liquid expectedResult = new Water(499L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        testSubject.storeLiquid(new Water(input - 1));
        Optional<Liquid> result = testSubject.fetchCurrentVolume();
        assertTrue(result
                .map(expectedResult::equals)
                .orElse(false));
    }

    @Test
    public void fetchCurrentVolumeReturnMaxCapacityWhenGivenValueOneLessThanTotalCapacityTwice() {
        long input = 500L;
        Liquid expectedResult = new Water(500L);
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        testSubject.storeLiquid(new Water(input - 1));
        testSubject.storeLiquid(new Water(input - 1));
        Optional<Liquid> result = testSubject.fetchCurrentVolume();
        assertTrue(result
                .map(expectedResult::equals)
                .orElse(false));
    }

    @Test
    public void fetchCurrentVolumeShouldReturnZeroWhenNoWaterHasBeenStored() {
        long input = 500L;
        SimpleLiquidContainer testSubject = new SimpleLiquidContainer(input);
        Optional<Liquid> result = testSubject.fetchCurrentVolume();
        assertFalse(result.isPresent());
    }
}