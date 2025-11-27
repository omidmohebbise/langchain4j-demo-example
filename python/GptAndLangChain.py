import os
from langchain_openai import ChatOpenAI
from langchain_core.messages import HumanMessage

# Point to OpenAI's API (GPT). Set a real API key in the environment.
os.environ["OPENAI_API_KEY"] = os.getenv("OPENAI_API_KEY", "")
os.environ["OPENAI_API_BASE"] = "https://api.openai.com/v1"

chat = ChatOpenAI(model="gpt-4.1-mini")  # or your model

response = chat.invoke([
    HumanMessage(content="Calculate this expression: x+5=2x-1")
])

print(response)
print(response.content)


