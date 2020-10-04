package me.time1015.utils.dinjector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InjectorTest {
  @Test
  public void inject_nullType_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testInjector(new Object()).inject(null));
  }

  @Test
  public void inject_noInstance_throwMissingDependency() {
    assertThrows(MissingDependencyException.class, () -> testInjector(null).inject(Object.class));
  }

  @Test
  public void injectOptional_nullType_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testInjector(new Object()).injectOptional(null));
  }

  @Test
  public void injectOptional_returnOptional() {
    assertNotNull(testInjector(new Object()).injectOptional(Object.class));
  }

  @Test
  public void injectOptional_noInstance_returnEmptyOptional() {
    assertTrue(testInjector(null).injectOptional(Object.class).isEmpty());
  }

  private TestInjector testInjector(Object toReturn) {
    return new TestInjector(toReturn);
  }
}
