USE [SapProjectDB]
GO

INSERT INTO [dbo].[ram]
           ([id]
           ,[manufacturer]
           ,[memory]
           ,[name])
     VALUES
           (1, 'Asus', 8, 'Asus - 8gb'),
           (2, 'Asus', 16, 'Asus - 16gb'),
           (3, 'MSI', 16, 'MSI - 16gb')
GO


USE [SapProjectDB]
GO

INSERT INTO [dbo].[storage]
           ([id]
           ,[capacity]
           ,[connector]
           ,[name]
           ,[type])
     VALUES
           (1, 1000, 'NVME M2', 'SSD memory connecting throught NVME M2 and has 1000GB capacity' ,'SSD'),
           (2, 2000, 'SATA', 'SSD memory connecting throught SATA and has 2000GB capacity' ,'HDD')
GO


USE [SapProjectDB]
GO

INSERT INTO [dbo].[screen]
           ([id]
           ,[diagonal]
           ,[name]
           ,[refresh rate]
           ,[response time])
     VALUES
           (1, 15.6, '15.6 inch with 144Hz display and 1ms response time!' , 144, 1),
           (2, 17.3, '17.3 inch with 120Hz display and 1ms response time!' , 120, 1),
           (3, 15.6, '15.6 inch with 60Hz display and 1ms response time!' , 60, 1)
GO


USE [SapProjectDB]
GO

INSERT INTO [dbo].[video_card]
           ([id]
           ,[manufactuer]
           ,[memory]
           ,[model]
           ,[name]
           ,[technology])
     VALUES
           (1, 'Nvidia', '6', 'GTX 1660','Nvidia GTX 1660 with 6GB DDR5 memory' ,'DDR5'),
           (2, 'Nvidia', '6', 'RTX 2060','Nvidia RTX 2060 with 6GB DDR6 memory' ,'DDR6')
GO


USE [SapProjectDB]
GO

INSERT INTO [dbo].[processor]
           ([id]
           ,[cores]
           ,[manufacturer]
           ,[model]
           ,[name]
           ,[speed])
     VALUES
           (1, 8, 'Intel', 'Core i7-9750h', 'Intel Core i7-9750h has 8 cores with 3.10GHz frequency', 3.10),
           (2, 8, 'Intel', 'Core i5-10300h', 'Intel Core i5-10300h has 8 cores with 2.80GHz frequency', 2.80)
GO


