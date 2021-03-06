USE [HotelBooking]
GO
ALTER TABLE [dbo].[HotelRoom] DROP CONSTRAINT [FK_HotelRoom_RoomType1]
GO
ALTER TABLE [dbo].[HotelRoom] DROP CONSTRAINT [FK_HotelRoom_Hotel]
GO
ALTER TABLE [dbo].[Hotel] DROP CONSTRAINT [FK_Hotel_Area]
GO
ALTER TABLE [dbo].[Discount] DROP CONSTRAINT [FK_Discount_Account]
GO
ALTER TABLE [dbo].[BookingDetail] DROP CONSTRAINT [FK_BookingDetail_HotelRoom]
GO
ALTER TABLE [dbo].[BookingDetail] DROP CONSTRAINT [FK_BookingDetail_Booking]
GO
/****** Object:  Table [dbo].[RoomType]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[RoomType]
GO
/****** Object:  Table [dbo].[HotelRoom]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[HotelRoom]
GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[Hotel]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[Discount]
GO
/****** Object:  Table [dbo].[BookingDetail]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[BookingDetail]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[Booking]
GO
/****** Object:  Table [dbo].[Area]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[Area]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP TABLE [dbo].[Account]
GO
USE [master]
GO
/****** Object:  Database [HotelBooking]    Script Date: 09/12/2021 7:07:20 PM ******/
DROP DATABASE [HotelBooking]
GO
/****** Object:  Database [HotelBooking]    Script Date: 09/12/2021 7:07:20 PM ******/
CREATE DATABASE [HotelBooking]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotelBooking', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HotelBooking_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HotelBooking] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotelBooking].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotelBooking] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotelBooking] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotelBooking] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotelBooking] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotelBooking] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotelBooking] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotelBooking] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotelBooking] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotelBooking] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotelBooking] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotelBooking] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotelBooking] SET RECOVERY FULL 
GO
ALTER DATABASE [HotelBooking] SET  MULTI_USER 
GO
ALTER DATABASE [HotelBooking] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotelBooking] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotelBooking] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotelBooking] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HotelBooking] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'HotelBooking', N'ON'
GO
USE [HotelBooking]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[email] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NULL,
	[phone] [nvarchar](255) NULL,
	[fullName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[createDate] [datetime] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Area]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Area](
	[areaId] [nvarchar](255) NOT NULL,
	[areaName] [nvarchar](255) NULL,
 CONSTRAINT [PK_Area] PRIMARY KEY CLUSTERED 
(
	[areaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Booking]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[bookingId] [nvarchar](255) NOT NULL,
	[hotelName] [nvarchar](255) NULL,
	[bookingBy] [nvarchar](255) NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[createDate] [datetime] NULL,
	[discount] [int] NULL,
	[realTotalPrice] [float] NULL,
	[totalPrice] [float] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Booking] PRIMARY KEY CLUSTERED 
(
	[bookingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BookingDetail]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingDetail](
	[bookingDetailId] [nvarchar](255) NOT NULL,
	[bookingId] [nvarchar](255) NULL,
	[hotelRoomId] [nvarchar](255) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[startDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_BookingDetail] PRIMARY KEY CLUSTERED 
(
	[bookingDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Discount]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[discountId] [nvarchar](255) NOT NULL,
	[discountPercent] [int] NULL,
	[discountFor] [nvarchar](255) NULL,
	[expirationDate] [datetime] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[discountId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Hotel]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Hotel](
	[hotelId] [nvarchar](255) NOT NULL,
	[hotelName] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[area] [nvarchar](255) NULL,
	[status] [int] NULL,
	[phone] [nvarchar](255) NULL,
 CONSTRAINT [PK_Hotel] PRIMARY KEY CLUSTERED 
(
	[hotelId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HotelRoom]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HotelRoom](
	[hotelRoomId] [nvarchar](255) NOT NULL,
	[hotelId] [nvarchar](255) NULL,
	[roomTypeId] [nvarchar](255) NULL,
	[description] [nvarchar](255) NULL,
	[quantity] [int] NULL,
	[currentPrice] [nvarchar](255) NULL,
 CONSTRAINT [PK_HotelRoom] PRIMARY KEY CLUSTERED 
(
	[hotelRoomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[RoomType]    Script Date: 09/12/2021 7:07:21 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoomType](
	[roomTypeId] [nvarchar](255) NOT NULL,
	[roomType] [nvarchar](255) NULL,
 CONSTRAINT [PK_RoomType] PRIMARY KEY CLUSTERED 
(
	[roomTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[Account] ([email], [password], [phone], [fullName], [address], [createDate], [status]) VALUES (N'anhnd16091998@gmail.com', N'8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', N'0985463522', N'D anh', N' 216 Ä. VÃµ VÄn NgÃ¢n, BÃ¬nh Thá», Thá»§ Äá»©c, ThÃ nh phá» Há» ChÃ­ Minh', CAST(N'2021-12-08 12:52:46.270' AS DateTime), 0)
INSERT [dbo].[Account] ([email], [password], [phone], [fullName], [address], [createDate], [status]) VALUES (N'chubao@gmail.com', N'8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', N'0984535322', N'Bao Le', N'Long Tháº¡nh Má»¹, Quáº­n 9, ThÃ nh phá» Há» ChÃ­ Minh', CAST(N'2021-12-09 09:03:54.950' AS DateTime), 0)
INSERT [dbo].[Account] ([email], [password], [phone], [fullName], [address], [createDate], [status]) VALUES (N'cuonghx@gmail.com', N'8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', N'093333423', N'Xuan Cuong', N'555B Đại lộ Bình Dương, Hiệp Thành, Thủ Dầu Một, Bình Dương', CAST(N'2021-12-03 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Account] ([email], [password], [phone], [fullName], [address], [createDate], [status]) VALUES (N'hieuthutha@gmail.com', N'8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', N'0982444356', N'Hieu Nguyen', N'54 HÆ°ng Äáº¡o VÆ°Æ¡ng, Trung DÅ©ng, ThÃ nh phá» BiÃªn HÃ²a, Äá»ng Nai', CAST(N'2021-12-09 19:02:51.260' AS DateTime), 0)
INSERT [dbo].[Account] ([email], [password], [phone], [fullName], [address], [createDate], [status]) VALUES (N'sadthuseetinh@gmail.com', N'8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', N'0985463522', N'Hai Hoang', N'QL51, Long BÃ¬nh TÃ¢n, ThÃ nh phá» BiÃªn HÃ²a, Äá»ng Nai, Viá»t Nam', CAST(N'2021-12-03 21:33:34.050' AS DateTime), 0)
INSERT [dbo].[Area] ([areaId], [areaName]) VALUES (N'a9459fc5-b9bf-4a7e-9a20-8dbedbac50ff', N'Đồng Nai')
INSERT [dbo].[Area] ([areaId], [areaName]) VALUES (N'ad377572-5064-4713-8790-5b3550b86226', N'Hà Nội')
INSERT [dbo].[Area] ([areaId], [areaName]) VALUES (N'd03b507e-f0ae-48a6-93f7-908c61da13bb', N'TP.HCM')
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'1a20e56d-3010-444b-805d-1161bd593def', N'Mira Central Park Hotel', N'hieuthutha@gmail.com', CAST(N'2021-12-24 00:00:00.000' AS DateTime), CAST(N'2021-12-25 00:00:00.000' AS DateTime), CAST(N'2021-12-09 19:05:24.813' AS DateTime), 10, 150, 135, 0)
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'46e6517a-444f-4dd7-9eb1-c8b315f2be61', N'Metropole Hotel', N'anhnd16091998@gmail.com', CAST(N'2021-12-15 00:00:00.000' AS DateTime), CAST(N'2021-12-17 00:00:00.000' AS DateTime), CAST(N'2021-12-09 09:00:01.610' AS DateTime), 0, 280, 280, 0)
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'71007fd2-93ba-46d1-b872-3a55ec0dfc44', N'Meraki Boutique Hotel', N'anhnd16091998@gmail.com', CAST(N'2021-12-23 00:00:00.000' AS DateTime), CAST(N'2021-12-29 00:00:00.000' AS DateTime), CAST(N'2021-12-09 08:45:38.313' AS DateTime), 0, 1680, 1680, -1)
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'99734920-2dbb-4c04-bef7-0eb6e03ac391', N'Hà Nội Golden Hotel', N'chubao@gmail.com', CAST(N'2021-12-17 00:00:00.000' AS DateTime), CAST(N'2021-12-21 00:00:00.000' AS DateTime), CAST(N'2021-12-09 12:45:38.920' AS DateTime), 0, 240, 240, 0)
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'a3ffe210-eaf9-482e-b8b0-813227e39b73', N'Meraki Boutique Hotel', N'sadthuseetinh@gmail.com', CAST(N'2021-12-17 00:00:00.000' AS DateTime), CAST(N'2021-12-27 00:00:00.000' AS DateTime), CAST(N'2021-12-09 07:13:17.707' AS DateTime), 0, 2100, 2100, 0)
INSERT [dbo].[Booking] ([bookingId], [hotelName], [bookingBy], [startDate], [endDate], [createDate], [discount], [realTotalPrice], [totalPrice], [status]) VALUES (N'ec09ac9b-36ad-4216-be72-f5755ac81329', N'Sheraton Sài Gòn', N'sadthuseetinh@gmail.com', CAST(N'2021-12-22 00:00:00.000' AS DateTime), CAST(N'2021-12-31 00:00:00.000' AS DateTime), CAST(N'2021-12-09 08:47:18.107' AS DateTime), 20, 630, 504, 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'47d27637-bec9-48c2-9d1a-540d0847695c', N'99734920-2dbb-4c04-bef7-0eb6e03ac391', N'f9654ecd-2fe3-4306-b70f-77b26e31894a', 240, 1, CAST(N'2021-12-17 00:00:00.000' AS DateTime), CAST(N'2021-12-21 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'782e2dd0-664b-45c7-a5bc-0b8495dab0d4', N'1a20e56d-3010-444b-805d-1161bd593def', N'4d18272c-6fef-4a68-b2ec-c3e15202db83', 150, 3, CAST(N'2021-12-24 00:00:00.000' AS DateTime), CAST(N'2021-12-25 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'ae8cecf8-6cbd-4cfc-9505-0abf4821c179', N'ec09ac9b-36ad-4216-be72-f5755ac81329', N'50419900-dddf-4dae-bb4c-7ae09f099f3c', 630, 1, CAST(N'2021-12-22 00:00:00.000' AS DateTime), CAST(N'2021-12-31 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'c60ad5ca-5469-468f-a9ae-d21ef08d881b', N'46e6517a-444f-4dd7-9eb1-c8b315f2be61', N'2d7b6942-efa0-492e-bbd8-603828ae503c', 280, 2, CAST(N'2021-12-15 00:00:00.000' AS DateTime), CAST(N'2021-12-17 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'e75ea2f0-26fc-4c37-8544-251941f842aa', N'a3ffe210-eaf9-482e-b8b0-813227e39b73', N'9d17d3dc-fc6c-4345-b3ee-5bfa519f1564', 2100, 3, CAST(N'2021-12-17 00:00:00.000' AS DateTime), CAST(N'2021-12-27 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[BookingDetail] ([bookingDetailId], [bookingId], [hotelRoomId], [price], [quantity], [startDate], [endDate], [status]) VALUES (N'ffab8116-5239-445c-8696-6b915b690993', N'71007fd2-93ba-46d1-b872-3a55ec0dfc44', N'9d17d3dc-fc6c-4345-b3ee-5bfa519f1564', 1680, 4, CAST(N'2021-12-23 00:00:00.000' AS DateTime), CAST(N'2021-12-29 00:00:00.000' AS DateTime), -1)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'6NX1', 20, N'sadthuseetinh@gmail.com', CAST(N'2021-12-11 00:00:00.000' AS DateTime), -1)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'DA14', 5, N'hieuthutha@gmail.com', CAST(N'2021-11-20 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'DAB4', 10, N'hieuthutha@gmail.com', CAST(N'2021-12-15 00:00:00.000' AS DateTime), -1)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'NYFP', 5, N'sadthuseetinh@gmail.com', CAST(N'2021-12-15 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'U670', 20, N'sadthuseetinh@gmail.com', CAST(N'2021-12-14 00:00:00.000' AS DateTime), -1)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'XJKX', 15, N'sadthuseetinh@gmail.com', CAST(N'2021-12-22 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Discount] ([discountId], [discountPercent], [discountFor], [expirationDate], [status]) VALUES (N'YI00', 10, N'cuonghx@gmail.com', CAST(N'2021-12-20 00:00:00.000' AS DateTime), 0)
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'36792f40-1c79-4df8-94e9-265791d072b5', N'Hotel Như Ý Biên Hòa', N'Bửu Long, Thành phố Biên Hòa, Đồng Nai', N'a9459fc5-b9bf-4a7e-9a20-8dbedbac50ff', 0, N'0982453221')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'3a51ae04-7942-46ca-b8c6-df5b80cf5acb', N'Hà Nội Golden Hotel', N'12 Ngõ Gạch, Hàng Buồm, Hoàn Kiếm, Hà Nội', N'ad377572-5064-4713-8790-5b3550b86226', 0, N'0335463772')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'47294ad3-97c0-4a85-bbe4-9ff7e27fa702', N'Mira Central Park Hotel', N'The Mira Central Park Hotel, 128/16 Đ. Nguyễn Ái Quốc, Tân Tiến, Thành phố Biên Hòa, Đồng Nai', N'a9459fc5-b9bf-4a7e-9a20-8dbedbac50ff', 0, N'0983939392')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'75587f6a-933a-4cc0-a3f2-32574fa88c02', N'Metropole Hotel', N'15 P. Ngô Quyền, Street, Hoàn Kiếm, Hà Nội', N'ad377572-5064-4713-8790-5b3550b86226', 0, N'0982333121')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'912c58db-4035-4506-b5e7-6eb86c5c267b', N'Hotel Nikko Saigon
', N'Nguyễn Văn Cừ, Phường Nguyễn Cư Trinh, Quận 1, Thành phố Hồ Chí Minh', N'd03b507e-f0ae-48a6-93f7-908c61da13bb', 0, N'02833345674')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'97a4175f-49b7-484b-ba7f-6bf35ab1d58b', N'Thịnh Vượng', N'88 Đồng Khởi, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', N'd03b507e-f0ae-48a6-93f7-908c61da13bb', 0, N'02838272828')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'a7d81e49-40fb-4cba-acb9-a9b172d18ca3', N'Mayflower Hotel Hanoi', N'11 Hàng Rươi, Hàng Mã, Hoàn Kiếm, Hà Nội', N'ad377572-5064-4713-8790-5b3550b86226', 0, N'0334561111')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'b673886c-8863-47ac-828f-780408f7f647', N'Bluebell Hotel', N'41 Ng. Huyện, Hàng Trống, Hoàn Kiếm, Hà Nội', N'ad377572-5064-4713-8790-5b3550b86226', 0, N'0982345666')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'b79229fa-d57a-439e-90f7-d923144ea614', N'Royal Hotel Bien Hoa', N'H4/7, Quater 6, Ward, Trung Dũng, Thành phố Biên Hòa, Đồng Nai', N'a9459fc5-b9bf-4a7e-9a20-8dbedbac50ff', 0, N'0795554321')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'cb15b69f-e4d9-4c0f-8d98-b60d7f92fc1c', N'Sheraton Sài Gòn', N'39-39A Đường Nguyễn Trung Trực, Phường Bến Thành, Quận 1, Thành phố Hồ Chí Minh', N'd03b507e-f0ae-48a6-93f7-908c61da13bb', 0, N'02838290029')
INSERT [dbo].[Hotel] ([hotelId], [hotelName], [address], [area], [status], [phone]) VALUES (N'dd7c2ae3-868e-4a6d-93c7-a0071802b97c', N'Meraki Boutique Hotel', N'178 Bùi Viện, Phường Phạm Ngũ Lão, Quận 1, Thành phố Hồ Chí Minh', N'd03b507e-f0ae-48a6-93f7-908c61da13bb', 0, N'0909888435')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'03de6760-58e7-11ec-bf63-0242ac130002', N'36792f40-1c79-4df8-94e9-265791d072b5', N'ccceb4ad-c934-458e-8fba-1fc8b3229beb', N'Phòng đôi 2 người mới', 23, N'70')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'27842a62-5570-11ec-bf63-0242ac130002', N'dd7c2ae3-868e-4a6d-93c7-a0071802b97c', N'c29f2dce-c495-426a-801c-a871de1453d3', N'Phong Gia Dinh', 44, N'50')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'2d7b6942-efa0-492e-bbd8-603828ae503c', N'75587f6a-933a-4cc0-a3f2-32574fa88c02', N'c29f2dce-c495-426a-801c-a871de1453d3', N'Phong Gia dinh vua', 50, N'70')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'2de48a06-54b1-11ec-bf63-0242ac130002', N'cb15b69f-e4d9-4c0f-8d98-b60d7f92fc1c', N'ccceb4ad-c934-458e-8fba-1fc8b3229beb', N'Phòng 2', 50, N'50')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'4d18272c-6fef-4a68-b2ec-c3e15202db83', N'47294ad3-97c0-4a85-bbe4-9ff7e27fa702', N'e6b9c057-000e-4875-b887-263d4990d5ea', N'Phòng đơn', 15, N'50')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'50419900-dddf-4dae-bb4c-7ae09f099f3c', N'cb15b69f-e4d9-4c0f-8d98-b60d7f92fc1c', N'c29f2dce-c495-426a-801c-a871de1453d3', N'Phòng dành cho 4 người', 45, N'70')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'50db2506-54b1-11ec-bf63-0242ac130002', N'cb15b69f-e4d9-4c0f-8d98-b60d7f92fc1c', N'e6b9c057-000e-4875-b887-263d4990d5ea', N'Phong 1', 33, N'70')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'9d17d3dc-fc6c-4345-b3ee-5bfa519f1564', N'dd7c2ae3-868e-4a6d-93c7-a0071802b97c', N'c29f2dce-c495-426a-801c-a871de1453d3', N'Phong Gia Dinh VIP', 23, N'70')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'db6865ba-58e6-11ec-bf63-0242ac130002', N'36792f40-1c79-4df8-94e9-265791d072b5', N'ccceb4ad-c934-458e-8fba-1fc8b3229beb', N'Phòng 1 tới 3', 55, N'65')
INSERT [dbo].[HotelRoom] ([hotelRoomId], [hotelId], [roomTypeId], [description], [quantity], [currentPrice]) VALUES (N'f9654ecd-2fe3-4306-b70f-77b26e31894a', N'3a51ae04-7942-46ca-b8c6-df5b80cf5acb', N'ccceb4ad-c934-458e-8fba-1fc8b3229beb', N'Phòng đôi', 60, N'60')
INSERT [dbo].[RoomType] ([roomTypeId], [roomType]) VALUES (N'c29f2dce-c495-426a-801c-a871de1453d3', N'Family')
INSERT [dbo].[RoomType] ([roomTypeId], [roomType]) VALUES (N'ccceb4ad-c934-458e-8fba-1fc8b3229beb', N'Double')
INSERT [dbo].[RoomType] ([roomTypeId], [roomType]) VALUES (N'e6b9c057-000e-4875-b887-263d4990d5ea', N'Single')
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_Booking] FOREIGN KEY([bookingId])
REFERENCES [dbo].[Booking] ([bookingId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_Booking]
GO
ALTER TABLE [dbo].[BookingDetail]  WITH CHECK ADD  CONSTRAINT [FK_BookingDetail_HotelRoom] FOREIGN KEY([hotelRoomId])
REFERENCES [dbo].[HotelRoom] ([hotelRoomId])
GO
ALTER TABLE [dbo].[BookingDetail] CHECK CONSTRAINT [FK_BookingDetail_HotelRoom]
GO
ALTER TABLE [dbo].[Discount]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Account] FOREIGN KEY([discountFor])
REFERENCES [dbo].[Account] ([email])
GO
ALTER TABLE [dbo].[Discount] CHECK CONSTRAINT [FK_Discount_Account]
GO
ALTER TABLE [dbo].[Hotel]  WITH CHECK ADD  CONSTRAINT [FK_Hotel_Area] FOREIGN KEY([area])
REFERENCES [dbo].[Area] ([areaId])
GO
ALTER TABLE [dbo].[Hotel] CHECK CONSTRAINT [FK_Hotel_Area]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [FK_HotelRoom_Hotel] FOREIGN KEY([hotelId])
REFERENCES [dbo].[Hotel] ([hotelId])
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [FK_HotelRoom_Hotel]
GO
ALTER TABLE [dbo].[HotelRoom]  WITH CHECK ADD  CONSTRAINT [FK_HotelRoom_RoomType1] FOREIGN KEY([roomTypeId])
REFERENCES [dbo].[RoomType] ([roomTypeId])
GO
ALTER TABLE [dbo].[HotelRoom] CHECK CONSTRAINT [FK_HotelRoom_RoomType1]
GO
USE [master]
GO
ALTER DATABASE [HotelBooking] SET  READ_WRITE 
GO
