package io.se7en.utils.dinjector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InjectorBuilderTest {
  @Test
  public void injecting_nullType_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testBuilder().injecting(null, new Object()));
  }

  @Test
  public void injecting_nullInstance_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testBuilder().injecting(Object.class, null));
  }

  @Test
  public void injecting_returnItself() {
    InjectorBuilder builder = testBuilder();

    assertSame(builder, builder.injecting(Object.class, new Object()));
  }

  @Test
  public void injectingLazily_nullType_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testBuilder().injectingLazily(null, Object::new));
  }

  @Test
  public void injectingLazily_nullSupplier_throwIllegalArgument() {
    assertThrows(IllegalArgumentException.class, () -> testBuilder().injectingLazily(Object.class, null));
  }

  @Test
  public void injectingLazily_returnItself() {
    InjectorBuilder builder = testBuilder();

    assertSame(builder, builder.injectingLazily(Object.class, Object::new));
  }

  @Test
  public void build_returnInjector() {
    assertNotNull(testBuilder().build());
  }

  @Nested
  public class BuildFromEagerInjectTest implements BuildTestContract {
    @Override
    public Injector buildInjectorToTest(Class<Object> type, Object instance) {
      return testBuilder().injecting(type, instance).build();
    }
  }

  @Nested
  public class BuildFromLazyInjectTest implements BuildTestContract {
    @Override
    public Injector buildInjectorToTest(Class<Object> type, Object instance) {
      return testBuilder().injectingLazily(type, () -> instance).build();
    }
  }

  private static interface BuildTestContract {
    Injector buildInjectorToTest(Class<Object> type, Object instance);

    @Test
    default void build_onInject_returnInjectedInstanceFromBuilder() {
      Object toInject = new Object();
      Class<Object> type = Object.class;

      assertSame(toInject, buildInjectorToTest(type, toInject).inject(type));
    }
  }

  private InjectorBuilder testBuilder() {
    return new InjectorBuilder();
  }
}
