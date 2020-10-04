package me.time1015.utils.dinjector;

import me.time1015.utils.dinjector.Injector;

final class TestInjector extends Injector {
  private final Object toReturn;

  TestInjector(Object toReturn) {
    this.toReturn = toReturn;
  }

  @Override
  protected <T> T onInject(Class<T> type) {
    return toReturn == null ? null : type.cast(toReturn);
  }
}
