package io.github.ovso.drive;

import lombok.Getter;

/**
 * Created by jaeho on 2018. 2. 6
 */

public enum Security {
  BASE_URL("https://api.punkapi.com");
  @Getter private String value;

  Security(String value) {
    this.value = value;
  }
}
