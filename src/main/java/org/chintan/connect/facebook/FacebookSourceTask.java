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

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FileStreamSourceTask reads from stdin or a file.
 */
public class FacebookSourceTask extends SourceTask {
    private static final Logger log = LoggerFactory.getLogger(FacebookSourceTask.class);
    final ConcurrentLinkedDeque<SourceRecord> messageQueue = new ConcurrentLinkedDeque<>();
    private FacebookClient facebookClient;
    private String accessToken;
    private FacebookClient.AccessToken token;
    private Scene scene;
    private Stage primaryStage;

    FacebookSourceConnectorConfig config;
    private int offset = 0;
    private String topic = null;

    private Long streamOffset;

    @Override
    public String version() {
        return new FacebookSourceConnector().version();
    }

    @Override
    public void start(Map<String, String> props) {
        this.config = new FacebookSourceConnectorConfig(props);
        facebookClient = new DefaultFacebookClient(this.config.accessToken, Version.VERSION_2_5);
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        List<SourceRecord> records = new ArrayList<>(256);

        while (records.isEmpty()) {
          //token = new DefaultFacebookClient(Version.VERSION_2_5).obtainAppAccessToken("182851062526884", "a6058b598aad7fce6657dcceac60966c");
          Page page = facebookClient.fetchObject(this.config.pageId, Page.class, Parameter.with("fields", "fan_count"));
          log.info("Page Fans: " + page.getFanCount());
          if (records.isEmpty()) {
            Thread.sleep(100);
          }
        }

        return records;
    }

    @Override
    public void stop() {
        log.trace("Stopping");
        
    }
}
