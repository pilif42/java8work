package com.philippe.app.service.mapper;

import com.philippe.app.representation.UserDTO;
import example.avro.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeanMapper extends ConfigurableMapper {
    /**
     * Set up the mapper for all our beans.
     * Only fields having non identical names need mapping. Others are mapped thanks to byDefault().
     *
     * @param factory the factory to which we add our mappings
     */
    protected final void configure(final MapperFactory factory) {
        factory.classMap(UserDTO.class, com.philippe.app.domain.User.class)
                .field("favouriteNumber", "favoriteNumber")
                .field("favouriteColor", "favoriteColor")
                .byDefault()
                .register();
        factory.classMap(com.philippe.app.domain.User.class, User.class)
                .field("favoriteNumber", "favorite_number")
                .field("favoriteColor", "favorite_color")
                .byDefault()
                .register();
        factory.classMap(UUID.class, String.class)
                .byDefault()
                .register();
    }
}
