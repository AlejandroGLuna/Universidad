library IEEE;
Use IEEE.STD_LOGIC_1164.ALL;
Use IEEE.STD_LOGIC_ARITH.ALL;
Use IEEE.STD_LOGIC_UNSIGNED.ALL;

Entity reloj_digital is

	port(reloj:in std_logic; 
		  reset,ajuste:in std_logic;
		  ajuste_hh,ajuste_mm:in std_logic;
		  display1,display2,display3,display4,display5,display6: out std_logic_vector(0 to 6));
		  
end reloj_digital;

architecture Behavioral of reloj_digital is

	--Señales de reloj para controlar cada bit referido al reloj
	signal clk0,clk1,clk2,clk3,clk4,clk5: std_logic := '0'; --señales del contador para cada display
	
	signal clk_a1,clk_a2:STD_LOGIC:='0'; --señales de ajuste minutos y horas de push button
	signal ajuste_sync:std_logic; -- señal de modo de ajuste en dipswitch
		
	constant max: integer := 50000000;
	constant med: integer := max/2;
	
	--Cuenta
	signal cuenta: integer range 0 to max; -- se asigna la señal de reloj
	
	-- Decodificador:

	function Deco(digito:integer) return std_logic_vector is
	
		variable display:std_logic_vector(0 to 6);
		
		begin
			case digito is
				when 0 => display := "0000001";--0
				when 1 => display := "1001111";--1
				when 2 => display := "0010010";--2
				when 3 => display := "0000110";--3
				when 4 => display := "1001100";--4
				when 5 => display := "0100100";--5
				when 6 => display := "1100000";--6
				when 7 => display := "0001111";--7
				when 8 => display := "0000000";--8
				when 9 => display := "0000100";--9
				when others => null;
			end case;
		return(display);
		end Deco;
	

Begin

-- Se establece el reloj a 1Hz lo equivalente a 1 segundo exacto
Divisor: process (reloj)

begin
	if(rising_edge(reloj) and reloj='1') then
		if(cuenta < max) then 
			cuenta <= cuenta + 1; --incrementa la cuenta
		else 
			cuenta <= 1; --reinicia la cuenta
		end if;
		if (cuenta <= med) then 
			clk0 <= '0';
		else 
			clk0 <='1';
		end if;
	end if;
end process;

-- Se definen los elementos del reloj 
Contador: process (clk0,reset,ajuste,ajuste_hh,ajuste_mm)

	variable uu_s: integer range 0 to 10;
	variable dd_s: integer range 0 to 6;
	variable uu_m: integer range 0 to 10;
	variable dd_m: integer range 0 to 6;
	variable uu_h: integer range 0 to 10;
	variable dd_h: integer range 0 to 3;
	
begin 

-- Se establece el reseteo del reloj
if (reset='0') then
	uu_s:=0;
	dd_s:=0;
	uu_m:=0;
	dd_m:=0;
	uu_h:=0;
	dd_h:=0;
else

-- Se establece el contador de segundos a 60 (unidades)

	if (rising_edge(clk0)  and clk0='1') then
	
		uu_s:= uu_s+1;
		clk1 <= '0';
		if (uu_s = 10) then
			uu_s := 0;
			clk1 <= '1';
			
		end if;
	end if;

-- (decenas)
	
	if (rising_edge(clk1) and clk1='1') then
	
		dd_s := dd_s + 1;
		clk2 <= '0';
		if (dd_s = 6) then
			dd_s := 0;
			clk2 <= '1';
			
		end if;
	end if;

-- Se establece la señal de ajuste de los segundos

-- Sincronizacion de segundos


	if (falling_edge(clk0) and clk0='0') then
		ajuste_sync <= ajuste;
	end if;
	
	clk_a1 <= (clk2 and not ajuste_sync) or (ajuste_mm and ajuste_sync);

-- Se establece el contador de minutos a 60 (Unidades)

	if (rising_edge(clk_a1) and clk_a1='1') then
	
		uu_m:= uu_m+1;
		clk3 <= '0';
		
		if (uu_m=10) then
			uu_m := 0;
			clk3 <= '1';
		end if;
	end if;
	
	--Decenas

	if (rising_edge(clk3) and clk3='1') then
	
		dd_m := dd_m + 1;
		clk4 <= '0';
		
		if (dd_m = 6) then
			dd_m := 0;
			clk4 <= '1';
		end if;
	end if;

-- Se establece la señal de ajuste a los minutos

	clk_a2 <= (clk4 and not ajuste_sync) or (ajuste_hh and ajuste_sync);

-- Se establece la señal de ajuste a las horas (unidades)

	if (rising_edge(clk_a2) and clk_a2='1') then
		uu_h := uu_h + 1;
		clk5 <= '0';
		
		if (uu_h = 10) then
			uu_h := 0;
			clk5 <= '1';
		end if;
	end if;

	--Decenas

	if (rising_edge(clk5) and clk5 = '1') then
		dd_h := dd_h+1;
	end if;
	if (dd_h = 2 and uu_h = 4) then
		uu_h := 0;
		dd_h := 0;
	end if;
end if;

-- Salida en display, usando la funcion Deco

display1 <= Deco(uu_s);
display2 <= Deco(dd_s);
display3 <= Deco(uu_m);
display4 <= Deco(dd_m);
display5 <= Deco(uu_h);
display6 <= Deco(dd_h);

end process;
end Behavioral;