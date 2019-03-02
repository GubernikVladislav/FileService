package ru.gubernik.fileservice.dao;

import ru.gubernik.fileservice.model.Role;

/**
 * Интерфейс Dao слоя для работы с таблице ролей
 */
public interface RoleDao {

    /**
     * Получение объекта роли по названию
     * @param roleName - название роли
     * @return - объект Role
     */
    Role getRole(String roleName);
}
