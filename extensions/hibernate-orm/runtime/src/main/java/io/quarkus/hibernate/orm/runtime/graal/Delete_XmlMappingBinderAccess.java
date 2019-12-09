package io.quarkus.hibernate.orm.runtime.graal;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Class org.hibernate.boot.spi.XmlMappingBinderAccess is
 * needed for XML mapping files, which are currently not supported by
 * Quarkus.
 * Excluding this should not be necessary as it should be a side-effect
 * of the dead code elimination, yet it's useful to delete this
 * explicitly so to catch any errors in the optimisations.
 */
@TargetClass(className = "org.hibernate.boot.spi.XmlMappingBinderAccess")
@Delete
public final class Delete_XmlMappingBinderAccess {
}
