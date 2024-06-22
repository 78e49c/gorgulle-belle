package com.example.gorgullebelle.app.data

val commonQuestionPrompts = listOf(
    Message("system", "Generate a question based on the concept and topic provided below." +
            " The question should be in English, and there should be a Turkish explanation of the question. " +
            "Provide four answer options with scores ranging from -2 to 2, indicating their correctness. " +
            "The response format should be as follows:"),
    Message("system", "explanation: \"Turkish explanation of the question\""),
    Message("system", "questionText: \"The question in English\""),
    Message("system", "answer1: \"Answer option 1\""),
    Message("system", "score1: \"Score for answer option 1\""),
    Message("system", "answer2: \"Answer option 2\""),
    Message("system", "score2: \"Score for answer option 2\""),
    Message("system", "answer3: \"Answer option 3\""),
    Message("system", "score3: \"Score for answer option 3\""),
    Message("system", "answer4: \"Answer option 4\""),
    Message("system", "score4: \"Score for answer option 4\""),
    Message("system", "Topic: General")
)

val konuTespitiPrompts = commonQuestionPrompts + listOf(
    Message("system", "Provide a slightly long English text, such as a passage from a book, " +
            "a wiki page, or an article, in the questionText field. In the explanation field, " +
            "provide a hint about the text's content, but do not reveal the answer explicitly. " +
            "The hint should be in Turkish."),
    Message("system", "The answer options should be Turkish concepts, names, or terms related to the text. " +
            "The most relevant concept should receive the highest score, while irrelevant concepts can " +
            "be used to mislead slightly, but not make the question too difficult.")
)

val gereklilikTespitiPrompts = commonQuestionPrompts + listOf(
    Message("system", "Describe a situation or event in English in the questionText field. " +
            "This situation should present a problem, oddity, or issue that the user needs to understand and find a solution for."),
    Message("system", "In the explanation field, provide hints about what the problem might be. " +
            "These hints should be in Turkish."),
    Message("system", "The answer options should be various items, actions, institutions, etc., " +
            "that could solve the problem described. These options should be in Turkish and could include things " +
            "like \"fire extinguisher\" if there is a fire, \"headphones\" if the issue is needing to listen to music in a library, and so on.")
)

val uygunEylemPrompts = commonQuestionPrompts + listOf(
    Message("system", "Provide dialogues in English in the questionText field. " +
            "These dialogues should involve either conversations with the user or between other characters."),
    Message("system", "In the explanation field, describe the context of the conversations, " +
            "such as where they are taking place, who is involved, what time it is, and other relevant details. " +
            "The explanation should be in Turkish."),
    Message("system", "The answer options should be actions in Turkish, such " +
            "as \"run away,\" \"nod your head no,\" \"smile,\" \"call the police,\" \"go to look,\" etc. " +
            "These actions should be contextually appropriate based on the dialogues provided.")
)

val questionPrompts = listOf(konuTespitiPrompts, gereklilikTespitiPrompts, uygunEylemPrompts)
