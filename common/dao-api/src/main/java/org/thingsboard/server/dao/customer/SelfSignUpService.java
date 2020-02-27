package org.thingsboard.server.dao.customer;

import org.thingsboard.server.common.data.User;
import org.thingsboard.server.common.data.id.TenantId;

public interface SelfSignUpService {
  User findCustomerByEmail(TenantId tenantId, String email);
}
