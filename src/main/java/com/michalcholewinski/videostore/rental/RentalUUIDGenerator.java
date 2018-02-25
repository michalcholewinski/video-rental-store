package com.michalcholewinski.videostore.rental;

import com.sun.corba.se.spi.ior.Identifiable;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class RentalUUIDGenerator implements IdentifierGenerator, Configurable {
    private String sequenceCallSyntax;

    @Override
    public void configure(
            Type type, Properties params, ServiceRegistry serviceRegistry)
            throws MappingException {
        final JdbcEnvironment jdbcEnvironment =
                serviceRegistry.getService(JdbcEnvironment.class);
        final Dialect dialect = jdbcEnvironment.getDialect();

        final String sequencePerEntitySuffix = ConfigurationHelper.getString(
                SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX,
                params,
                SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

        final String defaultSequenceName = ConfigurationHelper.getBoolean(
                SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY,
                params,
                false)
                ? params.getProperty(JPA_ENTITY_NAME) + sequencePerEntitySuffix
                : SequenceStyleGenerator.DEF_SEQUENCE_NAME;

        sequenceCallSyntax = dialect.getSequenceNextValString(
                ConfigurationHelper.getString(
                        SequenceStyleGenerator.SEQUENCE_PARAM,
                        params,
                        defaultSequenceName));
    }

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if (obj instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) obj;
            Serializable id = identifiable.getId();
            if (id != null) {
                return id;
            }
        }
        long seqValue = ((Number) Session.class.cast(session)
                .createSQLQuery(sequenceCallSyntax)
                .uniqueResult()).longValue();

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));

        return String.format("%1$04d-%2$tm%2$td-%2$tH%2$tM", seqValue, cal);
    }
}
