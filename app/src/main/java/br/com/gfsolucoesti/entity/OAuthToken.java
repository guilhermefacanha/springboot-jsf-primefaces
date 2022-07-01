package br.com.gfsolucoesti.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthToken implements Serializable {

  private static final long serialVersionUID = -163540925670678751L;

  @SerializedName("access_token")
  private String accessToken;

  @SerializedName("expire_in")
  private long expireIn;

  private String jti;

  @SerializedName("refresh_token")
  private String refreshToken;

  private String scope;

  @SerializedName("token_type")
  private String tokenType;

  public static OAuthToken fromJson(String json){
    return new Gson().fromJson(json, OAuthToken.class);
  }
}
