boostrap: ## Faz o setup do projecto com o nome escolhido
	sed -i "s/PROJECT_NAME/${name}/g" Dockerfile src/main/resources/application.properties.sample docker/init.sh
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'