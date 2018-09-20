/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package org.chintan.connect.facebook;

import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;
import org.apache.kafka.common.config.Config;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Very simple connector that works with the console. This connector supports both source and
 * sink modes via its 'mode' setting.
 */
public class FacebookSourceConnector extends SourceConnector {
    private static Logger log = LoggerFactory.getLogger(FacebookSourceConnector.class);
    Map<String, String> settings;
    private FacebookSourceConnectorConfig config;

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }

    @Override
    public void start(Map<String, String> props) {
        this.config = new FacebookSourceConnectorConfig(props);
        this.settings = props;
    }

    @Override
    public ConfigDef config() {
        return FacebookSourceConnectorConfig.conf();
    }

    @Override
    public Config validate(Map<String, String> connectorConfigs) {
        ConfigDef configDef = config();
        List<ConfigValue> configValues = configDef.validate(connectorConfigs);
        return new Config(configValues);
    }

    @Override
    public Class<? extends Task> taskClass() {
        return FacebookSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int i) {
      return Arrays.asList(this.settings);
    }

    @Override
    public void stop() {
        
    }
}