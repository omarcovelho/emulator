package br.com.omarcovelho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

import org.junit.jupiter.api.Test;

class RegistryTest {

  @Test
  public void shouldStartWithAllZeros() {
    Registry registry = new Registry();

    assertThat(registry.getValue(), equalTo(Byte.of(0)));
  }
}