package org.thingsboard.server.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.User;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.customer.SelfSignUpService;
import org.thingsboard.server.dao.user.UserService;
import lombok.extern.slf4j.Slf4j;

import static org.thingsboard.server.dao.service.Validator.validateString;

@Service
@Slf4j
public class SelfSignUpServiceImpl implements SelfSignUpService {

  public static final String NO_CUSTOMER_FOUND = "Customer not found ";
  public static TenantId tenantId;

  @Autowired
  private UserService userService;

  @Override
  public User findCustomerByEmail(TenantId tenantId, String email) {
    log.trace("Executing findUserByEmail[{}]", email);
    validateString(email, NO_CUSTOMER_FOUND + email);
    return userService.findUserByEmail(tenantId, email);
  }
}
