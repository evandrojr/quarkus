package io.quarkus.hibernate.orm.runtime.graal;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.TargetClass;

/**
 * Class org.hibernate.boot.archive.scan.spi.AbstractScannerImpl is
 * (usually) the base class for the Scanner implementations.
 * Excluding this should not be necessary as it should be a side-effect
 * of the dead code elimination as we plug a custom Scanner implementation,
 * yet it's useful to delete this explicitly so to catch any errors in
 * the optimisations.
 */
@TargetClass(className = "org.hibernate.boot.archive.scan.spi.AbstractScannerImpl")
@Delete
public final class Delete_AbstractScannerImpl {
}
