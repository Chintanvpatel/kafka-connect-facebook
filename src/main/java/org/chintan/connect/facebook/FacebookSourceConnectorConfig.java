/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chintan.connect.facebook;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class FacebookSourceConnectorConfig extends AbstractConfig {

  public static final String FACEBOOK_OAUTH_ACCESS_TOKEN_CONF = "facebook.accessToken";
  private static final String FACEBOOK_OAUTH_ACCESS_TOKEN_DOC = "Facebook access token";
  public static final String FACEBOOK_PAGE_ID_CONF = "facebook.pageId";
  private static final String FACEBOOK_PAGE_ID_DOC = "Facebook Page ID";
  public static final String KAFKA_STATUS_TOPIC_CONF = "kafka.topic";
  public static final String KAFKA_STATUS_TOPIC_DOC = "Kafka topic to write.";

  public final String topic;
  public final String accessToken;

  public FacebookSourceConnectorConfig(Map<String, String> parsedConfig) {
    super(conf(), parsedConfig);
    this.topic = this.getString(KAFKA_STATUS_TOPIC_CONF);
    this.accessToken = this.getString(FACEBOOK_OAUTH_ACCESS_TOKEN_CONF);
    this.pageId = this.getString(FACEBOOK_PAGE_ID_CONF);
  }

  public static ConfigDef conf() {
    return new ConfigDef()
        .define(FACEBOOK_OAUTH_ACCESS_TOKEN_CONF, Type.STRING, Importance.HIGH, FACEBOOK_OAUTH_ACCESS_TOKEN_DOC)
        .define(FACEBOOK_PAGE_ID_CONF, Type.STRING, Importance.HIGH, FACEBOOK_PAGE_ID_DOC)
        .define(KAFKA_STATUS_TOPIC_CONF, Type.STRING, Importance.HIGH, KAFKA_STATUS_TOPIC_DOC);
  }
}