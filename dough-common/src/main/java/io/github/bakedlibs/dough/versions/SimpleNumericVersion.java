package io.github.bakedlibs.dough.versions;

import javax.annotation.Nonnull;

import org.apache.commons.lang.Validate;

/**
 * This {@link Version} implementation consists of only one component, the version number.
 * 
 * @author TheBusyBiscuit
 *
 */
public class SimpleNumericVersion implements Version {

    private final int versionNumber;

    public SimpleNumericVersion(int version) {
        Validate.isTrue(version > 0, "The version must be a positive number.");

        this.versionNumber = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSimilar(@Nonnull Version version) {
        return version instanceof SimpleNumericVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNewerThan(@Nonnull Version version) {
        if (isSimilar(version)) {
            return asInteger() > ((SimpleNumericVersion) version).asInteger();
        } else {
            throw new IncomparableVersionsException(this, version);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEqualTo(@Nonnull Version version) {
        if (isSimilar(version)) {
            return asInteger() == ((SimpleNumericVersion) version).asInteger();
        } else {
            throw new IncomparableVersionsException(this, version);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOlderThan(@Nonnull Version version) {
        if (isSimilar(version)) {
            return asInteger() < ((SimpleNumericVersion) version).asInteger();
        } else {
            throw new IncomparableVersionsException(this, version);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nonnull String getAsString() {
        return String.valueOf(versionNumber);
    }

    public final int asInteger() {
        return versionNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return versionNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Version && isSimilar((Version) obj)) {
            return isEqualTo((Version) obj);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "NumericVersion [" + getAsString() + "]";
    }
}
