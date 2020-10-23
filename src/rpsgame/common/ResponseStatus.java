package rpsgame.common;

public enum ResponseStatus {

  OK(200000, "Request successful"),
  INVALID_PARAMS(400001, "Invalid params"),
  INVALID_CHOICE(400002, "Invalid choice"),
  INVALID_PLAYER(400003, "Player's name is too long(max. 20 chars). ");

  private final Integer code;
  private final String message;

  ResponseStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  /**
   * Get exception info by code.
   *
   * @param code {@code int}
   * @return {@code String}
   */
  public static String getMessage(int code) {
    for (ResponseStatus status : values()) {
      if (status.getCode().equals(code)) {
        return status.getMessage();
      }
    }
    return null;
  }


  @Override
  // TODO: need be jason foramt
  public String toString() {
    return "{ code:" + this.code + " message:" + message + "}";
  }
}
