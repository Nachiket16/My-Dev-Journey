JSONObject jsonObject = null;
  try {
      jsonObject = new JSONObject(json);
  } catch (JSONException e) {
      throw new RuntimeException(e);
  }
  log.info("Json Object : {}",jsonObject);
  jsonObject.remove("anchor_id");
