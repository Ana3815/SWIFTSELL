-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-07-2023 a las 00:53:06
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `swiftsell`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `message` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`id`, `name`, `message`, `email`) VALUES
(1, 'Ana', 'Hola me interesa el televisor ', ''),
(2, 'Ana', 'Hola me interesa el televisor ', ''),
(3, 'Ana', 'Hola me interesa el televisor ', ''),
(4, 'Ana', 'Hola me interesa el televisor ', 'ana@gmail.com'),
(5, 'Ana', 'Hola me interesa el televisor ', 'ana@gmail.com'),
(6, 'ANA', 'Me interesa el televisor', 'ana@gmail.com'),
(7, 'ANA', 'Me interesa el televisor', 'ana@gmail.com'),
(8, 'SAIRY', 'televisor', 'sairy@gmail.com'),
(9, 'SAIRY', 'televisor', 'sairy@gmail.com'),
(10, 'ANA', 'televisor', 'ana@gmail.com'),
(11, 'ANA', '123456', 'ana@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nuestros_productos`
--

CREATE TABLE `nuestros_productos` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `currency` varchar(200) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `nuestros_productos`
--

INSERT INTO `nuestros_productos` (`id`, `name`, `description`, `price`, `currency`, `photo`, `category`) VALUES
(1, 'Computador', 'computador asus con 32 de RAM', 2000000, '$', 'https://www.tecnoredes.net.co/wp-content/resources/2018/10/La-importancia-del-mantenimiento-del-computador-tecnoredes.jpg', 'Tecnología '),
(2, 'celular', 'celular xiaomi', 500000, '$', 'https://cnnespanol.cnn.com/wp-content/uploads/2021/03/Xiaomi-Mi-11-Lite-caracteristicas.jpg?quality=100&strip=info&w=320&h=240&crop=1', 'tecnologia'),
(3, 'Celular', 'celular xiaomi 12 note', 5000, '$', 'https://cnnespanol.cnn.com/wp-content/uploads/2021/03/Xiaomi-Mi-11-Lite-caracteristicas.jpg?quality=100&strip=info&w=320&h=240&crop=1', 'Laptops');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `otros_productos`
--

CREATE TABLE `otros_productos` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `price` int(10) NOT NULL,
  `currency` text NOT NULL,
  `photo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `otros_productos`
--

INSERT INTO `otros_productos` (`id`, `name`, `description`, `price`, `currency`, `photo`) VALUES
(1, 'Televisor', 'Televisor 32 pulgadas HD', 1000000, '$', 'https://www.semana.com/resizer/vp9nl1LV0I1MhL7BBqpmftIXMB8=/fit-in/768x0/smart/filters:format(jpg):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/semana/4GDPNFO3X5HKZC6KCFPXBWKTBQ.jpg'),
(2, 'celular', 'celular xiaomi', 500000, '$', 'https://cnnespanol.cnn.com/wp-content/uploads/2021/03/Xiaomi-Mi-11-Lite-caracteristicas.jpg?quality=100&strip=info&w=320&h=240&crop=1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `confirmPassword` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `confirmPassword`) VALUES
(1, 'Ana', 'ana@gmail.com', '123456', '123456'),
(2, 'ramon', 'ramon@gmail.com', '1234567', '1234567'),
(3, 'ramon', 'ramon@gmail.com', '1234567', '1234567'),
(4, 'ramon', 'ramon@gmail.com', '1234567', '1234567');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `nuestros_productos`
--
ALTER TABLE `nuestros_productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `otros_productos`
--
ALTER TABLE `otros_productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `nuestros_productos`
--
ALTER TABLE `nuestros_productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `otros_productos`
--
ALTER TABLE `otros_productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
