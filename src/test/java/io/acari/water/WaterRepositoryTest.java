package io.acari.water;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class WaterRepositoryTest {


  @Test
  public void fillContainerHalfWayShouldReturnAContainerThatIsHalfFullWheSupplyHasLessThanAsked() {
    WaterSupply waterSupply = Mockito.mock(WaterSupply.class);
    Mockito.when(waterSupply.fetchWater(250L)).thenReturn(20L);
     WaterRepository testSubject = new WaterRepository(waterSupply);
    LiquidContainer simpleLiquidContainer = new SimpleLiquidContainer(500);
    LiquidContainer result = testSubject.fillContainerHalfWay(simpleLiquidContainer);
    assertEquals(250, result.fetchCurrentVolume());
  }


  @Test
  public void fillContainerHalfWayShouldReturnAContainerThatIsHalfFullWheSupplyHasEnough() {
    WaterSupply waterSupply = Mockito.mock(WaterSupply.class);
    Mockito.when(waterSupply.fetchWater(250L)).thenReturn(250L);
    WaterRepository testSubject = new WaterRepository(waterSupply);
    LiquidContainer simpleLiquidContainer = new SimpleLiquidContainer(500);
    LiquidContainer result = testSubject.fillContainerHalfWay(simpleLiquidContainer);
    assertEquals(250, result.fetchCurrentVolume());
  }


  @Test
  public void fillContainerHalfWayShouldReturnAContainerThatIsHalfFullWheSupplyHasLessThanAsked_II() {
    WaterSupply waterSupply = Mockito.mock(WaterSupply.class);
    Mockito.when(waterSupply.fetchWater(500L)).thenReturn(50L);
   WaterRepository testSubject = new WaterRepository(waterSupply);
    LiquidContainer simpleLiquidContainer = new SimpleLiquidContainer(1000);
    LiquidContainer result = testSubject.fillContainerHalfWay(simpleLiquidContainer);
    assertEquals(500, result.fetchCurrentVolume());
  }


  @Test
  public void fillContainerHalfWayShouldReturnAContainerThatIsHalfFullWheSupplyHasEnough_II() {
    WaterSupply waterSupply = Mockito.mock(WaterSupply.class);
    Mockito.when(waterSupply.fetchWater(500L)).thenReturn(500L);
    WaterRepository testSubject = new WaterRepository(waterSupply);
    LiquidContainer simpleLiquidContainer = new SimpleLiquidContainer(1000);
    LiquidContainer result = testSubject.fillContainerHalfWay(simpleLiquidContainer);
    assertEquals(500, result.fetchCurrentVolume());
  }
}