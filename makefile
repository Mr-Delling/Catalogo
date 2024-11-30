# Nombre del proyecto y variables
APP_NAME = GestorCatalogo
SOURCE_DIR = src
BUILD_DIR = build
DOC_DIR = docs
JAR_FILE = $(APP_NAME).jar

# Encontrar todos los archivos .java
SOURCES := $(shell find $(SOURCE_DIR) -name "*.java")
CLASSES := $(SOURCES:$(SOURCE_DIR)/%.java=$(BUILD_DIR)/%.class)

# Compilación
.PHONY: all
all: compilar

compilar: $(CLASSES)

$(BUILD_DIR)/%.class: $(SOURCE_DIR)/%.java
	mkdir -p $(dir $@)
	javac -d $(BUILD_DIR) -sourcepath $(SOURCE_DIR) $<

# Generación del JAR
.PHONY: jar
jar: compilar
	jar cfe $(JAR_FILE) aplicacion.Main -C $(BUILD_DIR) .

# Generación de Javadoc
.PHONY: javadoc
javadoc:
	javadoc -d $(DOC_DIR) -sourcepath $(SOURCE_DIR) aplicacion dominio presentacion

# Limpieza
.PHONY: clean
clean:
	rm -rf $(BUILD_DIR) $(JAR_FILE) $(DOC_DIR)

# Ejecución
.PHONY: run
run: jar
	java -jar $(JAR_FILE)
