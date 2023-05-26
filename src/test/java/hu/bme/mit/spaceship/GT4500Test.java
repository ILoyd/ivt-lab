package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primaryMock;
  private TorpedoStore secondaryMock;

  @BeforeEach
  public void init(){
    primaryMock = mock(TorpedoStore.class);
    secondaryMock = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryMock,secondaryMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    when(primaryMock.fire(any(Integer.class))).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryMock,times(1)).fire(any(Integer.class));
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    when(primaryMock.fire(any(Integer.class))).thenReturn(true);
    when(secondaryMock.fire(any(Integer.class))).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryMock,times(1)).fire(any(Integer.class));
    verify(secondaryMock,times(1)).fire(any(Integer.class));
  }

 @Test
 public void fireTorpedo_Single_WhenFirstIsEmpty(){


    when(primaryMock.isEmpty()).thenReturn(true);
    when(secondaryMock.fire(any(Integer.class))).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true,result);
    verify(secondaryMock,times(1)).fire(any(Integer.class));

 } 

 @Test
 public void fireTorpedo_Single_WhenSecondIsEmpty(){

    when(secondaryMock.isEmpty()).thenReturn(true);
    when(primaryMock.fire(any(Integer.class))).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true,result);
    verify(primaryMock,times(1)).fire(any(Integer.class));

 } 

 @Test
 public void fireTorpedo_Single_WhenFirstIsCooling(){
    when(primaryMock.fire(any(Integer.class))).thenReturn(true);
    when(secondaryMock.fire(any(Integer.class))).thenReturn(true);

    boolean firstShot = ship.fireTorpedo(FiringMode.SINGLE);
    verify(primaryMock,times(1)).fire(any(Integer.class));
    assertEquals(true,firstShot);

    boolean secondShot = ship.fireTorpedo(FiringMode.SINGLE);

    assertEquals(true,secondShot);
    verify(secondaryMock,times(1)).fire(any(Integer.class));
 } 

@Test
public void fireTorpedo_Single_WhenSecondaryIsCooling(){
  when(primaryMock.fire(any(Integer.class))).thenReturn(true);
  when(secondaryMock.fire(any(Integer.class))).thenReturn(true);

  boolean firstShot = ship.fireTorpedo(FiringMode.SINGLE);
  assertEquals(true,firstShot);
  //verify(secondaryMock,times(1)).fire(any(Integer.class));

  boolean secondaryShot = ship.fireTorpedo(FiringMode.SINGLE);
  verify(primaryMock,times(1)).fire(any(Integer.class));
  assertEquals(true,secondaryShot);
}

@Test
public void fireTorpedo_All_WhenFirstIsEmpty(){
  when(primaryMock.isEmpty()).thenReturn(true);

  boolean result = ship.fireTorpedo(FiringMode.ALL);

  assertFalse(result);

}

@Test
public void fireTorpedo_All_WhenSecondIsEmpty(){
  when(secondaryMock.isEmpty()).thenReturn(true);

  boolean result = ship.fireTorpedo(FiringMode.ALL);

  assertFalse(result);

}




}


