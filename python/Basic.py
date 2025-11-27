"""Example that creates a LangChain LLM client.

This file attempts multiple import paths to be compatible with different
LangChain major versions and prints a helpful message when the required
provider packages are missing.
"""

llm = None
try:
    # Old-style import (pre-1.0)
    from langchain.llms import OpenAI

    llm = OpenAI(
        openai_api_base="http://localhost:1234/v1",
        openai_api_key="deepseek-r1-distill-qwen-7b",
    )
except Exception:
    try:
        # LangChain v1+: use the chat model factory (requires provider package)
        from langchain.chat_models import init_chat_model

        llm = init_chat_model(
            "openai:gpt-3.5-turbo",
            base_url="http://localhost:1234/v1",
            api_key="deepseek-r1-distill-qwen-7b",
        )
    except Exception as e:
        print(
            "Could not create a LangChain LLM client.\n"
            "Reason: {}\n".format(e)
            + "If you're running locally, create a virtual environment and install:\n"
            "  python3 -m venv .venv\n"
            "  .venv/bin/python -m pip install --upgrade pip\n"
            "  .venv/bin/python -m pip install langchain langchain-openai openai\n"
            "Or install system-wide if appropriate."
        )

if llm is not None:
    print("LLM client created:", llm)