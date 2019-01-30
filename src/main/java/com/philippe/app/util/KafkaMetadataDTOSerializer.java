package com.philippe.app.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.philippe.app.domain.KafkaMetadataDTO;

import java.lang.reflect.Type;

import static com.philippe.app.domain.KafkaMetadataDTO.UNSET_LONG;

public class KafkaMetadataDTOSerializer implements JsonSerializer<KafkaMetadataDTO> {

    private static final String OFFSET_PROPERTY = "offset";
    private static final String MIN_OFFSET_PROPERTY = "minOffset";
    private static final String MAX_OFFSET_PROPERTY = "maxOffset";

    @Override
    public JsonElement serialize(KafkaMetadataDTO kafkaMetadataDTO, Type type, JsonSerializationContext jsc) {
        JsonObject jObj = (JsonObject)new GsonBuilder().create().toJsonTree(kafkaMetadataDTO);
        if (kafkaMetadataDTO.getOffset() == UNSET_LONG){
            jObj.remove(OFFSET_PROPERTY);
        }
        if (kafkaMetadataDTO.getMinOffset() == UNSET_LONG){
            jObj.remove(MIN_OFFSET_PROPERTY);
        }
        if (kafkaMetadataDTO.getMaxOffset() == UNSET_LONG){
            jObj.remove(MAX_OFFSET_PROPERTY);
        }
        return jObj;
    }
}
