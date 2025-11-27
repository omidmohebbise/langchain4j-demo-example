import os
from langchain.chat_models import ChatOpenAI
from langchain.schema import HumanMessage

# Point to LM Studio's local OpenAI-compatible endpoint
os.environ["OPENAI_API_KEY"] = "sk-xxx"  # Dummy key, LM Studio ignores it
os.environ["OPENAI_API_BASE"] = "http://localhost:1234/v1"

chat = ChatOpenAI(model="deepseek-r1-distill-qwen-7b", temperature=0.7)

response = chat([HumanMessage(content="Calculate this expression: x+5=2x-1")])
print(response.content)