PGDMP      /            
    |            sic115    16.2    16.2 /    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    50047    sic115    DATABASE        CREATE DATABASE sic115 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_El Salvador.1252';
    DROP DATABASE sic115;
                postgres    false            �            1259    50048 	   cargo_seq    SEQUENCE     r   CREATE SEQUENCE public.cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.cargo_seq;
       public          postgres    false            �            1259    50049    cargo    TABLE       CREATE TABLE public.cargo (
    idservicio integer DEFAULT nextval('public.cargo_seq'::regclass) NOT NULL,
    nombre_cargo character varying(255) NOT NULL,
    salario_nominal double precision,
    dias_laborados double precision,
    horas_laborados double precision,
    recargo double precision,
    isss double precision,
    afp double precision,
    incaf double precision
);
    DROP TABLE public.cargo;
       public         heap    postgres    false    215            �            1259    50053    costos_indirectos_seq    SEQUENCE     ~   CREATE SEQUENCE public.costos_indirectos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.costos_indirectos_seq;
       public          postgres    false            �            1259    50054    costos_indirectos    TABLE       CREATE TABLE public.costos_indirectos (
    idindirectos integer DEFAULT nextval('public.costos_indirectos_seq'::regclass) NOT NULL,
    nombre_indirectos character varying(100),
    descripcion_indirectos character varying(300),
    costomes double precision
);
 %   DROP TABLE public.costos_indirectos;
       public         heap    postgres    false    217            �            1259    50058    cuenta    TABLE       CREATE TABLE public.cuenta (
    idcuenta integer NOT NULL,
    idtipo integer NOT NULL,
    idclasificacion integer NOT NULL,
    nombre_cuenta character varying(100) NOT NULL,
    debe_cuenta double precision NOT NULL,
    haber_cuenta double precision NOT NULL
);
    DROP TABLE public.cuenta;
       public         heap    postgres    false            �            1259    50061 	   empleados    TABLE       CREATE TABLE public.empleados (
    idempleado character varying(50) NOT NULL,
    nombres character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    salario double precision NOT NULL,
    isss character varying(100) NOT NULL,
    afp character varying(100) NOT NULL,
    cargo character varying(255),
    incaf character varying(100),
    costoreal character varying(100),
    cant_meses integer,
    septimo_dia double precision,
    vacaciones double precision,
    aguinaldo double precision
);
    DROP TABLE public.empleados;
       public         heap    postgres    false            �            1259    50066    periodo_contable    TABLE     �   CREATE TABLE public.periodo_contable (
    id integer NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL,
    cerrado boolean NOT NULL
);
 $   DROP TABLE public.periodo_contable;
       public         heap    postgres    false            �            1259    50069    periodo_contable_id_seq    SEQUENCE     �   CREATE SEQUENCE public.periodo_contable_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.periodo_contable_id_seq;
       public          postgres    false    221            �           0    0    periodo_contable_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.periodo_contable_id_seq OWNED BY public.periodo_contable.id;
          public          postgres    false    222            �            1259    50070 	   servicios    TABLE     �  CREATE TABLE public.servicios (
    id integer NOT NULL,
    idservicio integer NOT NULL,
    nombre_cliente character varying(100) NOT NULL,
    cantempleados integer NOT NULL,
    descripcion character varying(255) NOT NULL,
    costototal double precision,
    cantidad_meses integer,
    servicio character varying(100),
    precioventa double precision,
    mano_obra double precision,
    por_cif double precision
);
    DROP TABLE public.servicios;
       public         heap    postgres    false            �            1259    50073    subclasificacion_cuenta    TABLE     �   CREATE TABLE public.subclasificacion_cuenta (
    idclasificacion integer NOT NULL,
    idtipo integer NOT NULL,
    nombre_cuenta character varying(100) NOT NULL
);
 +   DROP TABLE public.subclasificacion_cuenta;
       public         heap    postgres    false            �            1259    50076 
   tipocuenta    TABLE     h   CREATE TABLE public.tipocuenta (
    idtipo integer NOT NULL,
    tipo_cuenta character varying(100)
);
    DROP TABLE public.tipocuenta;
       public         heap    postgres    false            �            1259    50079    tiposervicio    TABLE     {   CREATE TABLE public.tiposervicio (
    idservicio integer NOT NULL,
    nombre_servicio character varying(100) NOT NULL
);
     DROP TABLE public.tiposervicio;
       public         heap    postgres    false            �            1259    50082    transaccion    TABLE     �  CREATE TABLE public.transaccion (
    idtransaccion integer NOT NULL,
    idcuenta integer NOT NULL,
    nombre_cuenta character varying(100) NOT NULL,
    descripcion character varying(100) NOT NULL,
    fecha_transaccion date NOT NULL,
    debe_trans double precision NOT NULL,
    haber_trans double precision NOT NULL,
    saldo_acumulado double precision,
    total_debe double precision,
    total_haber double precision
);
    DROP TABLE public.transaccion;
       public         heap    postgres    false            B           2604    50085    periodo_contable id    DEFAULT     z   ALTER TABLE ONLY public.periodo_contable ALTER COLUMN id SET DEFAULT nextval('public.periodo_contable_id_seq'::regclass);
 B   ALTER TABLE public.periodo_contable ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �          0    50049    cargo 
   TABLE DATA           �   COPY public.cargo (idservicio, nombre_cargo, salario_nominal, dias_laborados, horas_laborados, recargo, isss, afp, incaf) FROM stdin;
    public          postgres    false    216   �<       �          0    50054    costos_indirectos 
   TABLE DATA           n   COPY public.costos_indirectos (idindirectos, nombre_indirectos, descripcion_indirectos, costomes) FROM stdin;
    public          postgres    false    218   ^=       �          0    50058    cuenta 
   TABLE DATA           m   COPY public.cuenta (idcuenta, idtipo, idclasificacion, nombre_cuenta, debe_cuenta, haber_cuenta) FROM stdin;
    public          postgres    false    219   �>       �          0    50061 	   empleados 
   TABLE DATA           �   COPY public.empleados (idempleado, nombres, apellidos, salario, isss, afp, cargo, incaf, costoreal, cant_meses, septimo_dia, vacaciones, aguinaldo) FROM stdin;
    public          postgres    false    220   �@       �          0    50066    periodo_contable 
   TABLE DATA           P   COPY public.periodo_contable (id, fecha_inicio, fecha_fin, cerrado) FROM stdin;
    public          postgres    false    221   �A       �          0    50070 	   servicios 
   TABLE DATA           �   COPY public.servicios (id, idservicio, nombre_cliente, cantempleados, descripcion, costototal, cantidad_meses, servicio, precioventa, mano_obra, por_cif) FROM stdin;
    public          postgres    false    223   �A       �          0    50073    subclasificacion_cuenta 
   TABLE DATA           Y   COPY public.subclasificacion_cuenta (idclasificacion, idtipo, nombre_cuenta) FROM stdin;
    public          postgres    false    224   {B       �          0    50076 
   tipocuenta 
   TABLE DATA           9   COPY public.tipocuenta (idtipo, tipo_cuenta) FROM stdin;
    public          postgres    false    225   C       �          0    50079    tiposervicio 
   TABLE DATA           C   COPY public.tiposervicio (idservicio, nombre_servicio) FROM stdin;
    public          postgres    false    226   tC       �          0    50082    transaccion 
   TABLE DATA           �   COPY public.transaccion (idtransaccion, idcuenta, nombre_cuenta, descripcion, fecha_transaccion, debe_trans, haber_trans, saldo_acumulado, total_debe, total_haber) FROM stdin;
    public          postgres    false    227   �C       �           0    0 	   cargo_seq    SEQUENCE SET     7   SELECT pg_catalog.setval('public.cargo_seq', 4, true);
          public          postgres    false    215                        0    0    costos_indirectos_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.costos_indirectos_seq', 9, true);
          public          postgres    false    217                       0    0    periodo_contable_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.periodo_contable_id_seq', 1, true);
          public          postgres    false    222            D           2606    50087    cargo cargo_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (idservicio);
 :   ALTER TABLE ONLY public.cargo DROP CONSTRAINT cargo_pkey;
       public            postgres    false    216            F           2606    50089 (   costos_indirectos costos_indirectos_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.costos_indirectos
    ADD CONSTRAINT costos_indirectos_pkey PRIMARY KEY (idindirectos);
 R   ALTER TABLE ONLY public.costos_indirectos DROP CONSTRAINT costos_indirectos_pkey;
       public            postgres    false    218            H           2606    50091    cuenta cuenta_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (idcuenta);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public            postgres    false    219            J           2606    50093    empleados empleados_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (idempleado);
 B   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_pkey;
       public            postgres    false    220            L           2606    50095 &   periodo_contable periodo_contable_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.periodo_contable
    ADD CONSTRAINT periodo_contable_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.periodo_contable DROP CONSTRAINT periodo_contable_pkey;
       public            postgres    false    221            N           2606    50097    servicios servicios_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT servicios_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.servicios DROP CONSTRAINT servicios_pkey;
       public            postgres    false    223            P           2606    50099 4   subclasificacion_cuenta subclasificacion_cuenta_pkey 
   CONSTRAINT        ALTER TABLE ONLY public.subclasificacion_cuenta
    ADD CONSTRAINT subclasificacion_cuenta_pkey PRIMARY KEY (idclasificacion);
 ^   ALTER TABLE ONLY public.subclasificacion_cuenta DROP CONSTRAINT subclasificacion_cuenta_pkey;
       public            postgres    false    224            R           2606    50101    tipocuenta tipocuenta_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.tipocuenta
    ADD CONSTRAINT tipocuenta_pkey PRIMARY KEY (idtipo);
 D   ALTER TABLE ONLY public.tipocuenta DROP CONSTRAINT tipocuenta_pkey;
       public            postgres    false    225            T           2606    50103    tiposervicio tiposervicio_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.tiposervicio
    ADD CONSTRAINT tiposervicio_pkey PRIMARY KEY (idservicio);
 H   ALTER TABLE ONLY public.tiposervicio DROP CONSTRAINT tiposervicio_pkey;
       public            postgres    false    226            V           2606    50105    transaccion transaccion_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT transaccion_pkey PRIMARY KEY (idtransaccion);
 F   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT transaccion_pkey;
       public            postgres    false    227            W           2606    50106 "   cuenta cuenta_idclasificacion_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_idclasificacion_fkey FOREIGN KEY (idclasificacion) REFERENCES public.subclasificacion_cuenta(idclasificacion);
 L   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_idclasificacion_fkey;
       public          postgres    false    219    224    4688            X           2606    50111    cuenta cuenta_idtipo_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_idtipo_fkey FOREIGN KEY (idtipo) REFERENCES public.tipocuenta(idtipo);
 C   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_idtipo_fkey;
       public          postgres    false    225    219    4690            Y           2606    50116 #   servicios servicios_idservicio_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicios
    ADD CONSTRAINT servicios_idservicio_fkey FOREIGN KEY (idservicio) REFERENCES public.tiposervicio(idservicio);
 M   ALTER TABLE ONLY public.servicios DROP CONSTRAINT servicios_idservicio_fkey;
       public          postgres    false    223    4692    226            Z           2606    50121 ;   subclasificacion_cuenta subclasificacion_cuenta_idtipo_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.subclasificacion_cuenta
    ADD CONSTRAINT subclasificacion_cuenta_idtipo_fkey FOREIGN KEY (idtipo) REFERENCES public.tipocuenta(idtipo);
 e   ALTER TABLE ONLY public.subclasificacion_cuenta DROP CONSTRAINT subclasificacion_cuenta_idtipo_fkey;
       public          postgres    false    225    224    4690            [           2606    50126 %   transaccion transaccion_idcuenta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT transaccion_idcuenta_fkey FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 O   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT transaccion_idcuenta_fkey;
       public          postgres    false    227    219    4680            �   o   x�m˱�0F���Lp�1g�Ea�4v��pљ�q���=�+�g1��T�֔E10&F���ּ�Ѽ������T�2o��G�0��Q���[�+�7�yr�|��"t$      �   s  x�U��N�0���S�*4?{t��Hl�xO����N��� ���m_�:M��*�u��c;���=�2vX3��>��Q��AK����&�VI3�2�(�wdmq�,/҈
"�;�1�)�PF&��#I� ��?شFT��M:Լ�g��2��n6���Ai��N�h���R��F^�]fa���P���Ag�[Ȁ��]�ә����l?��J�L(��)PY��w����� �tc�s���$�}}�5���H!�-�B�b[Uu]ܶ������u ��-��aיzly�iA���Tŉz�y�e��/
(**�U:Z#�%��00ڭr�W뻵?�~�O(c/�y/`k�$2,�o�Ǔ�i�%��i      �   �  x�e�Mn�0���)t�A$�HSL�E1���F�ـ�"z$9��F]t�#�bCYV;��+���G*�V�?%��ň�X��^�����С	H�U���=HzF���:�k���>#�DQ�`�&TB���F^�ɟ�qS���d&�d$��A]�����gN��"�
VE�ƱH�}O����V�)�%���ӧ�Ǝ�љ�k���ޔt�'�:� V�-�v�Ws6�.�p'�⌜yӶ���eo�>@��;�<]��.�m�W��h�������"y����Mɸ����!��K���LT���D�ĕ�s�ZKޟޭGK��Kr��cL��W�f����X���"~֓������1�[���m*qa��R����EXڅ�f��N@>�m�~q�*7���u9:��?�X�V"]�      �   )  x����J1���S���d�y,�҂�%v�,Ĥdۃ}�<��b&K��7��4$�����J%���'��O!�Η��Cy
1�RQ�@H���F�M�|)9F?�HQ W�Pk�3@n)W�z���!
8RΙ2�	dK��������vj��P�ٓ��q�����/�����8��9�@���6y��ɪ��4d�%�oi��l��C8�{"��ł�J��T�!�D�n揶�!�ۘ[(��lEh���ιcj��w(�5��ց��b���1T��:�ȴ�J��jm.�VU�H����I��      �      x�3�4202�54�50�3�8Ӹb���� V1      �   y   x�=�;�  �z9Ş��#j�T�ivȎ���c���7Zؼ�Y�0ӳ���.��q%�;!�|�����?�lr��C��d?�p���m���ҥɵg�	��gi�.�5j�J�.b&�      �   �   x�U�=
�0Fg�>AA��^:�n9@� (��L���3�Y��x|D@pOM�R1S�M��I���<Ɠ^�� �������vv	Fx孃��Ф�q"���޺)U���3��ت+{om�9'�����?�      �   R   x�3�tL.�,��2�H,1����������|.Nϼ�����b.SN��� Ì3 �(%3%�X�R!=1/1/93��+F��� �^�      �   z   x��K
AםS�	?�z7�'�L^����~f\U�r��0C�����!�K8��x��>ޖ���Yӥ<�S\O��舔��t*�n��.c��2r�*K���l�<�
y[2�      �   t  x���Mn�0���)x�$�c{��h�E� 	��f,3.�TH�@��EO��u(Ziā�^XC��G�1'g�l�U�ԙJA==*��3���gYu�h�`"�����?��{�.���eB���e���T��*C�P�ҫ�н��i�i-�H��F
��r��H� řf�}����-݆��r��ćt�"�)��2�^�m��-�~P���M2%�s��>X�L�y������u�����v}���v�Ҧ6U��p�7(���t��<��UX�,_����?���Y��aPTG���C��-�[3y�_� ����@��WZ���X��1��`�JV,y��6�3��i{L7�;�\���K/�6���@�e�^���v�|��2���uqzh�(�����OӢ?��5G)��<BX�$�#�g��̗H|]�a�4s2R�~��zX�{i������+&��f�J�8�M[���F�6�r-���\��nF�6�fN�=f���PB
�����/k��΄.�I�W�+�e�PWɡ/���C!�����Џ���=ԴVXA��O�@j�(�V��O�H�gc�T4#��zl.�cl��j1v�4�`�_��9L���􆔉�\,���     