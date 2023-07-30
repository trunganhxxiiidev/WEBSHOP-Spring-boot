use WEBSHOP;
--adjust bit null
ALTER TABLE Users
ALTER COLUMN Enabled BIT NULL

--get name constraint 
SELECT name
FROM sys.default_constraints
WHERE parent_object_id = OBJECT_ID('Users')
AND parent_column_id = COLUMNPROPERTY(OBJECT_ID('Users'), 'Enabled', 'ColumnId');

-- Modify the existing default constraint to set the default value to 0
ALTER TABLE Users
DROP CONSTRAINT DF_Customers_Active;

ALTER TABLE Users
ADD CONSTRAINT DF_Customers_Active DEFAULT 0 FOR Enabled;

CREATE TRIGGER tr_User_SetDefaultEnabled
ON Users after insert
as
BEGIN
    SET NOCOUNT ON;
    
    -- Nếu cột Enabled không được cung cấp trong câu lệnh INSERT, gán giá trị mặc định là 0
    IF NOT EXISTS (SELECT 1 FROM inserted WHERE Enabled IS NOT NULL)
    BEGIN
        UPDATE Users
        SET Enabled = 0
        FROM Users u
        INNER JOIN inserted i ON u.username = i.username; -- Giả sử 'id' là cột khóa chính của bảng User, hãy thay thế nếu cần thiết
    END
END;

