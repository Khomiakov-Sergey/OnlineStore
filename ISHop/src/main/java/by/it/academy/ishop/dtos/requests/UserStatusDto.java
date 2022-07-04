package by.it.academy.ishop.dtos.requests;

import by.it.academy.ishop.entities.user.Status;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserStatusDto {
    @Enumerated(EnumType.STRING)
    Status status;
}
