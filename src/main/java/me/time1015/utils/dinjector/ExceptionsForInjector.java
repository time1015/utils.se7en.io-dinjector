package me.time1015.utils.dinjector;

final class ExceptionsForInjector {
  public static IllegalArgumentException nullType() {
    return new IllegalArgumentException("Null type to inject");
  }

  public static MissingDependencyException missing(Class<?> type) {
    return new MissingDependencyException(type.getName());
  }
}
