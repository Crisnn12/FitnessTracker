package com.fitnesstracker.app;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;

import java.util.HashMap;
import java.util.Map;

public class RecetarioController {

    @FXML private ComboBox<String> comboFiltro;
    @FXML private ListView<String> listaRecetas;
    @FXML private TextArea detalleReceta;
    private ObservableList<String> recetas = FXCollections.observableArrayList();
    private Map<String, String> detalles = new HashMap<>();

    @FXML
    public void initialize() {

        listaRecetas.setOnMouseClicked(event -> {
            String recetaSeleccionada = listaRecetas.getSelectionModel().getSelectedItem();
            if (recetaSeleccionada != null) {
                String nombreReceta = recetaSeleccionada.split("\n")[0];
                String descripcion = detalles.get(nombreReceta);
                detalleReceta.setText(descripcion != null ? descripcion : "Descripción no disponible.");
            }
        });

        comboFiltro.setItems(FXCollections.observableArrayList(
                "Bajar peso", "Mantener peso", "Ganar masa muscular"
        ));

        comboFiltro.setOnAction(e -> cargarRecetas(comboFiltro.getValue()));
    }


    private void cargarRecetas(String objetivo) {
        recetas.clear();
        detalles.clear();

        switch (objetivo) {
            case "Bajar peso":
                recetas.add("🥗 Ensalada de pollo con quinoa\n400 kcal | 30g Prot | 20g Carbs | 15g Grasas");
                detalles.put("🥗 Ensalada de pollo con quinoa", "Pechuga de pollo a la plancha sobre quinoa cocida, espinaca, tomate cherry, pepino y aceite de oliva. Ideal para una comida ligera y rica en proteína.");

                recetas.add("🍵 Sopa de verduras\n150 kcal | 5g Prot | 20g Carbs | 3g Grasas");
                detalles.put("🍵 Sopa de verduras", "Caldo casero con zanahoria, apio, zapallo, porotos verdes y cebolla. Muy saciante con pocas calorías.");

                recetas.add("🥗 Ensalada verde con atún\n280 kcal | 25g Prot | 10g Carbs | 12g Grasas");
                detalles.put("🥗 Ensalada verde con atún", "Atún en agua con lechuga, rúcula, palta y tomate. Aderezo de limón y aceite de oliva.");

                recetas.add("🍛 Curry de garbanzos light\n300 kcal | 15g Prot | 35g Carbs | 8g Grasas");
                detalles.put("🍛 Curry de garbanzos light", "Garbanzos con leche de coco light, cúrcuma, espinaca y tomate. Rico en fibra y sin carne.");

                recetas.add("🍳 Omelette de claras y espinaca\n180 kcal | 20g Prot | 2g Carbs | 7g Grasas");
                detalles.put("🍳 Omelette de claras y espinaca", "Hecho solo con claras, relleno de espinaca salteada y cebolla. Muy bajo en calorías.");

                recetas.add("🥒 Rollitos de pepino con hummus\n200 kcal | 8g Prot | 15g Carbs | 10g Grasas");
                detalles.put("🥒 Rollitos de pepino con hummus", "Láminas de pepino rellenas con hummus, zanahoria rallada y pimiento. Snack saludable y rápido.");

                recetas.add("🍗 Pollo grillado con puré de coliflor\n350 kcal | 32g Prot | 10g Carbs | 14g Grasas");
                detalles.put("🍗 Pollo grillado con puré de coliflor", "Filete de pollo a la plancha acompañado de puré cremoso de coliflor bajo en carbohidratos.");

                recetas.add("🍝 Zoodles con salsa de tomate\n250 kcal | 10g Prot | 20g Carbs | 10g Grasas");
                detalles.put("🍝 Zoodles con salsa de tomate", "Fideos de zapallito italiano con salsa natural de tomate y albahaca. Alternativa light a la pasta.");

                recetas.add("🍳 Tostada integral con palta y huevo\n350 kcal | 18g Prot | 25g Carbs | 18g Grasas");
                detalles.put("🍳 Tostada integral con palta y huevo", "Pan integral con puré de palta, huevo pochado y semillas de chía.");

                recetas.add("🥣 Yogur natural con chía y frutos rojos\n220 kcal | 14g Prot | 18g Carbs | 8g Grasas");
                detalles.put("🥣 Yogur natural con chía y frutos rojos", "Postre saludable: yogur descremado con frambuesas, arándanos y semillas de chía.");

                recetas.add("🥗 Wrap de lechuga con carne magra\n290 kcal | 25g Prot | 10g Carbs | 12g Grasas");
                detalles.put("🥗 Wrap de lechuga con carne magra", "Hojas de lechuga rellenas con carne molida magra, tomate, cebolla y aguacate.");

                recetas.add("🍳 Revuelto de tofu con vegetales\n300 kcal | 18g Prot | 12g Carbs | 15g Grasas");
                detalles.put("🍳 Revuelto de tofu con vegetales", "Tofu firme salteado con espinaca, champiñones y cúrcuma.");

                recetas.add("🥙 Pita integral con pavo y vegetales\n330 kcal | 28g Prot | 25g Carbs | 10g Grasas");
                detalles.put("🥙 Pita integral con pavo y vegetales", "Pan pita relleno con fiambre de pavo, lechuga, zanahoria rallada y mostaza.");

                recetas.add("🥣 Crema de zapallo light\n180 kcal | 6g Prot | 25g Carbs | 6g Grasas");
                detalles.put("🥣 Crema de zapallo light", "Sopa cremosa hecha con zapallo, cebolla y leche vegetal. Ideal para cenas livianas.");

                recetas.add("🍤 Salteado de camarones con verduras\n340 kcal | 30g Prot | 20g Carbs | 12g Grasas");
                detalles.put("🍤 Salteado de camarones con verduras", "Camarones con brócoli, morrón y zanahoria en salsa de soja baja en sodio.");

                recetas.add("🍗 Pollo al limón con ensalada fresca\n370 kcal | 35g Prot | 15g Carbs | 14g Grasas");
                detalles.put("🍗 Pollo al limón con ensalada fresca", "Pollo cocido con limón, ajo y especias, acompañado de ensalada de rúcula y tomate.");

                recetas.add("🍛 Estofado de pavo con zapallo\n320 kcal | 28g Prot | 18g Carbs | 10g Grasas");
                detalles.put("🍛 Estofado de pavo con zapallo", "Cubos de pavo cocidos con zapallo, cebolla y pimientos. Rico y ligero.");

                recetas.add("🥬 Bowl verde con huevo duro\n290 kcal | 20g Prot | 18g Carbs | 14g Grasas");
                detalles.put("🥬 Bowl verde con huevo duro", "Mezcla de lechuga, rúcula, espinaca, palta y huevo duro con semillas.");

                recetas.add("🥚 Huevos duros con palta y tomate\n270 kcal | 18g Prot | 10g Carbs | 18g Grasas");
                detalles.put("🥚 Huevos duros con palta y tomate", "Huevos cocidos con rodajas de tomate y palta. Desayuno rápido y saciante.");

                recetas.add("🥣 Porridge de avena con proteína\n350 kcal | 25g Prot | 30g Carbs | 8g Grasas");
                detalles.put("🥣 Porridge de avena con proteína", "Avena cocida con agua, polvo de proteína, canela y banana en rodajas.");

                break;
            case "Mantener peso":
                recetas.add("🍽️ Pollo al curry con arroz integral\n550 kcal | 40g Prot | 50g Carbs | 20g Grasas");
                detalles.put("🍽️ Pollo al curry con arroz integral\n550 kcal | 40g Prot | 50g Carbs | 20g Grasas",
                        "Trozos de pechuga de pollo cocinados con cebolla, ajo y curry suave, acompañados de arroz integral al vapor. Un platillo equilibrado en proteínas, carbohidratos complejos y grasas saludables, ideal para mantener tu energía durante el día.");

                recetas.add("🥙 Wrap de pavo y hummus\n480 kcal | 35g Prot | 40g Carbs | 18g Grasas");
                detalles.put("🥙 Wrap de pavo y hummus\n480 kcal | 35g Prot | 40g Carbs | 18g Grasas",
                        "Wrap integral relleno de pechuga de pavo, hojas verdes, tomate, zanahoria rallada y hummus. Perfecto para un almuerzo liviano que mantiene la saciedad sin pasarte de calorías.");

                recetas.add("🍝 Pasta integral con vegetales y tofu\n520 kcal | 28g Prot | 55g Carbs | 15g Grasas");
                detalles.put("🍝 Pasta integral con vegetales y tofu\n520 kcal | 28g Prot | 55g Carbs | 15g Grasas",
                        "Pasta integral salteada con tofu dorado, brócoli, morrones y un toque de salsa de soja baja en sodio. Una receta vegetariana balanceada que aporta energía sin exceso.");

                recetas.add("🥩 Filete de ternera con puré de boniato\n600 kcal | 45g Prot | 45g Carbs | 22g Grasas");
                detalles.put("🥩 Filete de ternera con puré de boniato\n600 kcal | 45g Prot | 45g Carbs | 22g Grasas",
                        "Jugoso filete de ternera a la plancha acompañado de un cremoso puré de boniato con un toque de canela. Excelente fuente de hierro y energía.");

                recetas.add("🍛 Lentejas guisadas con arroz integral\n530 kcal | 30g Prot | 50g Carbs | 18g Grasas");
                detalles.put("🍛 Lentejas guisadas con arroz integral\n530 kcal | 30g Prot | 50g Carbs | 18g Grasas",
                        "Guiso de lentejas con tomate, zanahoria y especias, acompañado de arroz integral. Rico en fibra y proteínas vegetales, ideal para mantener un peso saludable.");

                recetas.add("🍤 Fideos de arroz con langostinos y verduras\n500 kcal | 35g Prot | 48g Carbs | 14g Grasas");
                detalles.put("🍤 Fideos de arroz con langostinos y verduras\n500 kcal | 35g Prot | 48g Carbs | 14g Grasas",
                        "Fideos de arroz salteados con langostinos, brócoli, cebolla morada y salsa de sésamo. Un plato con buen contenido proteico y muy sabroso.");

                recetas.add("🍚 Buddha bowl de salmón y vegetales\n540 kcal | 38g Prot | 45g Carbs | 20g Grasas");
                detalles.put("🍚 Buddha bowl de salmón y vegetales\n540 kcal | 38g Prot | 45g Carbs | 20g Grasas",
                        "Bowl con salmón al horno, arroz integral, palta, zanahoria, pepino y col morada. Un platillo colorido y balanceado en macronutrientes.");

                recetas.add("🌮 Tacos de pollo con guacamole\n510 kcal | 36g Prot | 40g Carbs | 18g Grasas");
                detalles.put("🌮 Tacos de pollo con guacamole\n510 kcal | 36g Prot | 40g Carbs | 18g Grasas",
                        "Tortillas de maíz rellenas con pollo grillado, cebolla, cilantro y guacamole casero. Ricos en proteína y grasas buenas.");

                recetas.add("🥔 Papas al horno con yogur griego y atún\n490 kcal | 33g Prot | 45g Carbs | 17g Grasas");
                detalles.put("🥔 Papas al horno con yogur griego y atún\n490 kcal | 33g Prot | 45g Carbs | 17g Grasas",
                        "Papas horneadas con relleno de atún y yogur griego, condimentadas con especias frescas. Saciante y nutritivo.");

                recetas.add("🍳 Omelette de champiñones con pan integral\n450 kcal | 30g Prot | 28g Carbs | 20g Grasas");
                detalles.put("🍳 Omelette de champiñones con pan integral\n450 kcal | 30g Prot | 28g Carbs | 20g Grasas",
                        "Omelette de huevos con champiñones y queso bajo en grasa, acompañado de pan integral tostado. Perfecto para desayuno o cena ligera.");
                recetas.add("🍝 Pasta integral con verduras y atún\n500 kcal | 35g Prot | 45g Carbs | 18g Grasas");
                detalles.put("🍝 Pasta integral con verduras y atún", "Una combinación nutritiva de pasta integral con vegetales salteados (pimiento, brócoli, zanahoria) y atún en agua. Se saltea con un poco de aceite de oliva y especias al gusto. Ideal para un almuerzo balanceado y saciante.");

                recetas.add("🌯 Wrap de pavo y hummus\n420 kcal | 28g Prot | 38g Carbs | 16g Grasas");
                detalles.put("🌯 Wrap de pavo y hummus", "Tortilla integral rellena con pechuga de pavo, hummus, lechuga, tomate y zanahoria rallada. Una opción rápida y equilibrada para llevar.");

                recetas.add("🥔 Papas asadas con lentejas y espinaca\n470 kcal | 25g Prot | 52g Carbs | 14g Grasas");
                detalles.put("🥔 Papas asadas con lentejas y espinaca", "Papas horneadas con lentejas cocidas y espinaca salteada con ajo. Un plato vegetariano que ofrece saciedad y buenos nutrientes.");

                recetas.add("🍳 Omelette de espinaca y champiñones\n390 kcal | 30g Prot | 10g Carbs | 25g Grasas");
                detalles.put("🍳 Omelette de espinaca y champiñones", "Huevos batidos con espinaca fresca y champiñones salteados. Rico en proteínas y grasas saludables, ideal para el desayuno o cena.");

                recetas.add("🥘 Guiso de garbanzos con arroz integral\n510 kcal | 27g Prot | 55g Carbs | 18g Grasas");
                detalles.put("🥘 Guiso de garbanzos con arroz integral", "Garbanzos cocidos en un guiso con tomate, cebolla, pimiento y arroz integral. Rico en fibra, proteína vegetal y energía duradera.");

                recetas.add("🍲 Sopa de lentejas con zanahoria y apio\n350 kcal | 22g Prot | 35g Carbs | 10g Grasas");
                detalles.put("🍲 Sopa de lentejas con zanahoria y apio", "Una sopa casera nutritiva con lentejas, zanahoria, apio y condimentos. Excelente para una comida ligera pero saciante.");

                recetas.add("🥑 Tostadas de palta con huevo pochado\n430 kcal | 20g Prot | 35g Carbs | 25g Grasas");
                detalles.put("🥑 Tostadas de palta con huevo pochado", "Pan integral tostado con palta (aguacate) y huevo pochado encima. Rico en grasas buenas y proteínas, ideal para mantener el peso.");

                recetas.add("🍗 Pechuga de pollo con puré de camote\n480 kcal | 38g Prot | 40g Carbs | 14g Grasas");
                detalles.put("🍗 Pechuga de pollo con puré de camote", "Pechuga de pollo a la plancha con un puré suave de camote cocido y sazonado. Una comida principal equilibrada en macros.");

                recetas.add("🍛 Curry de tofu con arroz basmati\n500 kcal | 30g Prot | 48g Carbs | 20g Grasas");
                detalles.put("🍛 Curry de tofu con arroz basmati", "Tofu salteado con cebolla, pimientos y curry suave en leche de coco. Acompañado con arroz basmati. Una opción vegana y sabrosa.");

                recetas.add("🍠 Ensalada tibia de batata, pollo y rúcula\n460 kcal | 34g Prot | 40g Carbs | 15g Grasas");
                detalles.put("🍠 Ensalada tibia de batata, pollo y rúcula", "Batata asada en cubos con pollo desmenuzado y hojas de rúcula, aliñada con aceite de oliva y limón. Perfecta como comida ligera o cena.");

                break;
            case "Ganar masa muscular":
                recetas.add("🥩 Filete de res con papas al romero\n650 kcal | 50g Prot | 40g Carbs | 30g Grasas");
                detalles.put("🥩 Filete de res con papas al romero\n650 kcal | 50g Prot | 40g Carbs | 30g Grasas",
                        "Filete de res a la parrilla acompañado de papas horneadas con romero y aceite de oliva. Rico en proteínas y calorías para apoyar el desarrollo muscular.");

                recetas.add("🍝 Pasta integral con pollo y brócoli\n620 kcal | 45g Prot | 55g Carbs | 20g Grasas");
                detalles.put("🍝 Pasta integral con pollo y brócoli\n620 kcal | 45g Prot | 55g Carbs | 20g Grasas",
                        "Pasta integral salteada con pechuga de pollo y brócoli en una salsa ligera de yogur. Ideal para después del entrenamiento.");

                recetas.add("🥑 Tostadas de aguacate con huevo y salmón\n580 kcal | 32g Prot | 38g Carbs | 28g Grasas");
                detalles.put("🥑 Tostadas de aguacate con huevo y salmón\n580 kcal | 32g Prot | 38g Carbs | 28g Grasas",
                        "Tostadas integrales cubiertas con aguacate, huevo pochado y lonjas de salmón ahumado. Alta densidad calórica con grasas saludables.");

                recetas.add("🍛 Pollo tikka masala con arroz\n670 kcal | 48g Prot | 60g Carbs | 25g Grasas");
                detalles.put("🍛 Pollo tikka masala con arroz\n670 kcal | 48g Prot | 60g Carbs | 25g Grasas",
                        "Clásico plato de pollo marinado con especias y yogur en una salsa cremosa, acompañado con arroz blanco. Excelente aporte de calorías y sabor.");

                recetas.add("🥓 Omelette de 4 huevos con queso y espinaca\n600 kcal | 42g Prot | 5g Carbs | 40g Grasas");
                detalles.put("🥓 Omelette de 4 huevos con queso y espinaca\n600 kcal | 42g Prot | 5g Carbs | 40g Grasas",
                        "Omelette alto en proteínas con queso fundido y espinaca fresca. Ideal para desayuno o cena post-entreno.");

                recetas.add("🍗 Pollo al horno con puré de camote\n630 kcal | 47g Prot | 50g Carbs | 22g Grasas");
                detalles.put("🍗 Pollo al horno con puré de camote\n630 kcal | 47g Prot | 50g Carbs | 22g Grasas",
                        "Muslos de pollo horneados con especias acompañado de un cremoso puré de camote. Comida completa y energética.");

                recetas.add("🥙 Burrito de carne y frijoles negros\n710 kcal | 40g Prot | 60g Carbs | 32g Grasas");
                detalles.put("🥙 Burrito de carne y frijoles negros\n710 kcal | 40g Prot | 60g Carbs | 32g Grasas",
                        "Tortilla integral rellena de carne molida, frijoles negros, arroz, queso y guacamole. Compacto, completo y delicioso.");

                recetas.add("🥘 Risotto de champiñones con parmesano\n580 kcal | 25g Prot | 55g Carbs | 26g Grasas");
                detalles.put("🥘 Risotto de champiñones con parmesano\n580 kcal | 25g Prot | 55g Carbs | 26g Grasas",
                        "Risotto cremoso de arroz arborio con champiñones salteados y queso parmesano. Textura cremosa y aporte energético.");

                recetas.add("🍳 Desayuno power: huevos, avena y fruta\n600 kcal | 35g Prot | 50g Carbs | 22g Grasas");
                detalles.put("🍳 Desayuno power: huevos, avena y fruta\n600 kcal | 35g Prot | 50g Carbs | 22g Grasas",
                        "Desayuno completo: huevos revueltos, avena cocida en leche y fruta fresca. Gran manera de comenzar el día.");

                recetas.add("🍔 Hamburguesa casera con pan integral\n720 kcal | 45g Prot | 45g Carbs | 35g Grasas");
                detalles.put("🍔 Hamburguesa casera con pan integral\n720 kcal | 45g Prot | 45g Carbs | 35g Grasas",
                        "Carne magra en pan integral con queso, lechuga, tomate y aguacate. Ideal para una comida abundante y balanceada.");
                recetas.add("🥥 Curry de garbanzos con leche de coco\n640 kcal | 28g Prot | 55g Carbs | 30g Grasas");
                detalles.put("🥥 Curry de garbanzos con leche de coco\n640 kcal | 28g Prot | 55g Carbs | 30g Grasas",
                        "Plato vegano con garbanzos cocidos en leche de coco, tomate y especias, acompañado de arroz basmati. Rico en calorías y proteínas vegetales.");

                recetas.add("🌮 Tacos de pescado con aguacate y arroz\n610 kcal | 36g Prot | 48g Carbs | 24g Grasas");
                detalles.put("🌮 Tacos de pescado con aguacate y arroz\n610 kcal | 36g Prot | 48g Carbs | 24g Grasas",
                        "Tacos de filete de pescado con col morada, aguacate, crema y arroz. Perfecto balance entre sabor, proteína y grasa saludable.");

                recetas.add("🍲 Estofado de ternera con lentejas\n700 kcal | 50g Prot | 40g Carbs | 30g Grasas");
                detalles.put("🍲 Estofado de ternera con lentejas\n700 kcal | 50g Prot | 40g Carbs | 30g Grasas",
                        "Estofado nutritivo con ternera tierna, lentejas y verduras. Excelente opción alta en hierro, proteína y fibra.");

                recetas.add("🍕 Pizza integral de pollo y champiñones\n680 kcal | 42g Prot | 60g Carbs | 28g Grasas");
                detalles.put("🍕 Pizza integral de pollo y champiñones\n680 kcal | 42g Prot | 60g Carbs | 28g Grasas",
                        "Pizza casera con masa integral, pechuga de pollo, champiñones y queso bajo en grasa. Alta en proteína y muy sabrosa.");

                recetas.add("🍚 Arroz con huevo, atún y vegetales\n590 kcal | 38g Prot | 48g Carbs | 22g Grasas");
                detalles.put("🍚 Arroz con huevo, atún y vegetales\n590 kcal | 38g Prot | 48g Carbs | 22g Grasas",
                        "Arroz salteado con huevo revuelto, atún y vegetales mixtos. Fácil de preparar y cargado de nutrientes.");

                recetas.add("🥩 Lomo de cerdo con batata asada\n660 kcal | 48g Prot | 45g Carbs | 27g Grasas");
                detalles.put("🥩 Lomo de cerdo con batata asada\n660 kcal | 48g Prot | 45g Carbs | 27g Grasas",
                        "Lomo de cerdo jugoso con batata al horno y especias. Rico en proteínas y carbohidratos complejos.");

                recetas.add("🥜 Smoothie de avena, mantequilla de maní y plátano\n700 kcal | 35g Prot | 55g Carbs | 32g Grasas");
                detalles.put("🥜 Smoothie de avena, mantequilla de maní y plátano\n700 kcal | 35g Prot | 55g Carbs | 32g Grasas",
                        "Batido denso en calorías con avena cocida, plátano, mantequilla de maní y proteína en polvo. Ideal como snack post-entreno.");

                recetas.add("🍳 Tarta de huevo con espinaca y pavo\n620 kcal | 40g Prot | 35g Carbs | 30g Grasas");
                detalles.put("🍳 Tarta de huevo con espinaca y pavo\n620 kcal | 40g Prot | 35g Carbs | 30g Grasas",
                        "Tarta salada con base de avena y huevo, rellena con espinaca y jamón de pavo. Alta en proteína y muy saciante.");

                recetas.add("🥘 Chili con carne y arroz integral\n690 kcal | 45g Prot | 50g Carbs | 28g Grasas");
                detalles.put("🥘 Chili con carne y arroz integral\n690 kcal | 45g Prot | 50g Carbs | 28g Grasas",
                        "Clásico chili tex-mex con carne picada, frijoles y arroz integral. Excelente aporte de energía y proteína.");

                recetas.add("🍗 Wrap de pavo, hummus y vegetales\n620 kcal | 38g Prot | 45g Carbs | 26g Grasas");
                detalles.put("🍗 Wrap de pavo, hummus y vegetales\n620 kcal | 38g Prot | 45g Carbs | 26g Grasas",
                        "Wrap integral con pechuga de pavo, hummus, lechuga y tomate. Fácil de llevar, completo en macronutrientes.");
                break;
        }
        listaRecetas.setItems(recetas);

    }
}
