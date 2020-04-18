package io.se7en.utils.dinjector;

final class ExceptionsForInjectorBuilder {
  public static IllegalArgumentException nullType() {
    return new IllegalArgumentException("Null type to inject");
  }

  public static IllegalArgumentException nullInstance() {
    return new IllegalArgumentException("Null type to inject");
  }

  public static IllegalArgumentException nullSupplier() {
    return new IllegalArgumentException("Null type to inject");
  }
}
