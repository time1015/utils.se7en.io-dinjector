package me.time1015.utils.dinjector;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class InjectorBuilder {
  private final Map<Class<?>, Supplier<?>> map;

  public InjectorBuilder() {
    this.map = new IdentityHashMap<>();
  }

  public <T> InjectorBuilder injecting(Class<T> type, T instance) {
    if (type == null)
      throw ExceptionsForInjectorBuilder.nullType();

    if (instance == null)
      throw ExceptionsForInjectorBuilder.nullInstance();

    map.put(type, () -> instance);
    return this;
  }

  public <T> InjectorBuilder injectingLazily(Class<T> type, Supplier<T> supplier) {
    if (type == null)
      throw ExceptionsForInjectorBuilder.nullType();

    if (supplier == null)
      throw ExceptionsForInjectorBuilder.nullSupplier();

    map.put(type, supplier);
    return this;
  }

  public Injector build() {
    return new BuiltInjector(Map.copyOf(map));
  }

  private static final class BuiltInjector extends Injector {
    private final Map<Class<?>, Supplier<?>> map;

    private BuiltInjector(Map<Class<?>, Supplier<?>> map) {
      this.map = map;
    }

    @Override
    protected <T> T onInject(Class<T> type) {
      return type.cast(map.get(type).get());
    }
  }
}
