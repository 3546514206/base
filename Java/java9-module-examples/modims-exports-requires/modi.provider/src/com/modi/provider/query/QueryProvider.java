package com.modi.provider.query;

import com.mod.impl.ModuleQuery;
import com.modi.api.query.Query;
import com.pack.impl.PackageQuery;

import java.util.Optional;

import static com.modi.provider.util.Flags.*;

/**
 * Created by hakan on 17/11/2017
 */
public class QueryProvider {

    public static Optional<Query> getQuery(String queryName) {

        if (queryName.equals(MODULE_FLAG) || queryName.equals(M_FLAG)) {
            return Optional.of(new ModuleQuery());
        } else if (queryName.equals(PACKAGE_FLAG) || queryName.equals(P_FLAG)) {
            return Optional.of(new PackageQuery());
        }

        return Optional.empty();
    }
}
