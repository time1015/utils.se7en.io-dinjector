package me.time1015.utils.dinjector;

import java.util.Optional;

public abstract class Injector {
  public final <T> T inject(Class<T> type) {
    if (type == null)
      throw ExceptionsForInjector.nullType();

    T toInject = onInject(type);
    if (toInject == null)
      throw ExceptionsForInjector.missing(type);

    return toInject;
  }

  public final <T> Optional<T> injectOptional(Class<T> type) {
    if (type == null)
      throw ExceptionsForInjector.nullType();

    return Optional.ofNullable(onInject(type));
  }

  protected abstract <T> T onInject(Class<T> type);
}
